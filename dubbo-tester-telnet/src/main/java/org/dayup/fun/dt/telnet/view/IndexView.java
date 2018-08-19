package org.dayup.fun.dt.telnet.view;

import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.table.WebTable;
import org.dayup.fun.dt.telnet.conf.Configs;
import org.dayup.fun.dt.telnet.conf.ViewConf;
import org.dayup.fun.dt.telnet.dto.DubboApi;
import org.dayup.fun.dt.telnet.dto.DubboMethod;
import org.dayup.fun.dt.telnet.view.enums.MenuAction;
import org.dayup.fun.dt.telnet.view.listener.MenuActionListener;
import org.dayup.fun.dt.telnet.view.table.CellButtonCallback;
import org.dayup.fun.dt.telnet.view.table.CellButtonRenderer;
import org.dayup.fun.dt.telnet.view.table.DubboApiTableModel;
import org.dayup.fun.dt.telnet.view.table.DubboMethodTableModel;
import org.dayup.fun.dt.telnet.view.table.CellButtonEditor;
import org.dayup.fun.dt.telnet.view.service.TelnetService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class IndexView extends AbstractView {

    private ViewConf viewConf = Configs.getViewConf();

    private TelnetService telnetService;

    private WebHotkeyLabel connServerLabel;

    private WebTable duApiTable;

    private JTable duMethodTable;

    private DubboApiTableModel dubboApiModel;

    private DubboMethodTableModel dubboMethodModel;

    @Override
    protected void setLayout() {
        telnetService = new TelnetService(this);
        // 主框架
        mainFrame = new JFrame("Dubbo Tester");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = viewConf.getWindowWidth();
        int height = viewConf.getWindowHeight();
        int x = (int) (toolkit.getScreenSize().getWidth() - width) / 2;
        int y = (int) (toolkit.getScreenSize().getHeight() - height) / 2;
        mainFrame.setLocation(x, y);
        mainFrame.setSize(width, height);

        addMenuBar();
        addContent();

    }


    @Override
    protected void addListeners() {
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                viewConf.store();
                System.exit(0);
            }
        });
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension dimension = e.getComponent().getSize();
                viewConf.setWindowWidth(dimension.width);
                viewConf.setWindowHeight(dimension.height);
            }
        });

        duApiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = duApiTable.getSelectedRow();
                if (row == -1) {
                    return;
                }
                DubboApi api = dubboApiModel.getRowAt(row);
                refreshMethods(telnetService.queryApiMethods(api));
            }
        });

        mainFrame.setVisible(true);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();  //创建菜单工具栏
        mainFrame.setJMenuBar(menuBar);
        JMenu jm = new JMenu(msg("index.menu.options"));
        JMenuItem newConn = new JMenuItem(msg("index.menu.options.new_connect"));
        newConn.addActionListener(new MenuActionListener(MenuAction.NEW_CONN, telnetService));
        JMenuItem openConn = new JMenuItem(msg("index.menu.options.open_connect"));
        openConn.addActionListener(new MenuActionListener(MenuAction.OPEN_CONN, telnetService));
        jm.add(newConn);
        jm.add(openConn);
        menuBar.add(jm);
    }

    private void addContent() {

        // Top part content
        WebPanel topPanel = new WebPanel(false);
        WebLabel serverLabel = new WebLabel(msg("index.label.server"));
        connServerLabel = new WebHotkeyLabel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(serverLabel);
        topPanel.add(connServerLabel);

        // Bottom part content
        WebPanel bottomPanel = new WebPanel(false, dubboWebPane());
        // Split
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setMargin(5);
        splitPane.setDividerLocation(40);
        splitPane.setDrawDividerBorder(false);
        splitPane.setOneTouchExpandable(false);
        getWindow().getContentPane().add(splitPane);
    }


    public WebSplitPane dubboWebPane() {
        // top panel
        WebPanel topPanel = new WebPanel(false);
        dubboApiModel = new DubboApiTableModel();
        duApiTable = new WebTable();
        duApiTable.setModel(dubboApiModel);
        duApiTable.setRowHeight(30);
        duApiTable.setAutoResizeMode(WebTable.AUTO_RESIZE_ALL_COLUMNS);
        duApiTable.setFillsViewportHeight(true);
        duApiTable.setRowSelectionAllowed(true);
        topPanel.add(new WebScrollPane(duApiTable));

        // bottom panel
        WebPanel bottomPanel = new WebPanel(false);
        dubboMethodModel = new DubboMethodTableModel();
        duMethodTable = new JTable();
        duMethodTable.setModel(dubboMethodModel);
        duMethodTable.setRowHeight(30);
        duMethodTable.setColumnSelectionAllowed(true);
        duMethodTable.setAutoResizeMode(WebTable.AUTO_RESIZE_OFF);
        dubboMethodModel.initColumnSizes(duMethodTable);
        addCellListener(duMethodTable.getColumnModel().getColumn(0));
        bottomPanel.add(new WebScrollPane(duMethodTable));
        // Split Panel
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDrawDividerBorder(false);
        splitPane.setContinuousLayout(false);
        return splitPane;

    }

    private void addCellListener(TableColumn invokeColumn) {
        invokeColumn.setCellRenderer(new CellButtonRenderer(msg("index.button.test")));
        invokeColumn.setCellEditor(new CellButtonEditor(msg("index.button.test"), new CellButtonCallback() {
            @Override
            public void callback(Object value) {
                if (value == null) {
                    return;
                }
                new InvokeView((DubboMethod) value, telnetService).init();
            }
        }));

    }


    public void setConnServerInfo(String serverInfo) {
        connServerLabel.setText(serverInfo);
    }

    public void refreshApis(List<DubboApi> data) {
        dubboApiModel.setData(data);
    }

    public void refreshMethods(List<DubboMethod> data) {
        dubboMethodModel.setData(data);
    }
}
