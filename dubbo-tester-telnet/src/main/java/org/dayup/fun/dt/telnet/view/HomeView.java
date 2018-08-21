package org.dayup.fun.dt.telnet.view;

import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.table.WebTable;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.bean.DuService;
import org.dayup.fun.dt.telnet.supplier.enums.MenuAction;
import org.dayup.fun.dt.telnet.view.component.dubbo.InvokeView;
import org.dayup.fun.dt.telnet.view.listener.MenuActionListener;
import org.dayup.fun.dt.telnet.view.support.ButtonClickCallback;
import org.dayup.fun.dt.telnet.view.support.TableCellButtonRenderer;
import org.dayup.fun.dt.telnet.view.support.DuServiceTableModel;
import org.dayup.fun.dt.telnet.view.support.DuMethodTableModel;
import org.dayup.fun.dt.telnet.view.support.TableCellButtonEditor;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class HomeView extends BaseView {

    private JFrame window;

    private HomePresenter homePresenter;

    private WebHotkeyLabel connServerLabel;

    private WebTable duApiTable;

    private JTable duMethodTable;

    private DuServiceTableModel duServiceModel;

    private DuMethodTableModel duMethodModel;

    @Override
    protected void setLayout() {
        homePresenter = new HomePresenter(this);
        window = new WebFrame(msg("app.name"));
        int width = configs().getHomeWidth();
        int height = configs().getHomeHeight();
        window.pack();
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        addMenuBar();
        addContent();

        initWindow(window);
    }


    @Override
    protected void addListeners() {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                configs().store();
                System.exit(0);
            }
        });
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension dimension = e.getComponent().getSize();
                configs().setHomeSize(dimension.width, dimension.height);
            }
        });

        duApiTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = duApiTable.getSelectedRow();
                if (row == -1) {
                    return;
                }
                DuService service = duServiceModel.getRowAt(row);
                refreshMethods(homePresenter.dubboMethods(service));
            }
        });

        window.setVisible(true);
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();  //创建菜单工具栏
        window.setJMenuBar(menuBar);
        JMenu jm = new JMenu(msg("home.menu.server"));
        JMenuItem newConn = new JMenuItem(msg("home.menu.server.new"));
        newConn.addActionListener(new MenuActionListener(MenuAction.SERVER_NEW, homePresenter));
        JMenuItem openConn = new JMenuItem(msg("home.menu.server.open"));
        openConn.addActionListener(new MenuActionListener(MenuAction.SERVER_OPEN, homePresenter));
        jm.add(newConn);
        menuBar.add(jm);
    }

    private void addContent() {

        // Top part getWindow
        WebPanel topPanel = new WebPanel(false);
        WebLabel serverLabel = new WebLabel(msg("home.label.server"));
        connServerLabel = new WebHotkeyLabel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(serverLabel);
        topPanel.add(connServerLabel);

        // Bottom part getWindow
        WebPanel bottomPanel = new WebPanel(false, dubboWebPane());
        // Split
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setMargin(10);
        splitPane.setDividerLocation(40);
        window.add(splitPane);
    }


    public WebSplitPane dubboWebPane() {
        // top panel
        WebPanel topPanel = new WebPanel(false);
        duServiceModel = new DuServiceTableModel();
        duApiTable = new WebTable();
        duApiTable.setModel(duServiceModel);
        duApiTable.setRowHeight(30);
        duApiTable.setFillsViewportHeight(true);
        duApiTable.setRowSelectionAllowed(true);
        topPanel.add(new WebScrollPane(duApiTable));

        // bottom panel
        WebPanel bottomPanel = new WebPanel(false);
        duMethodModel = new DuMethodTableModel();
        duMethodTable = new JTable();
        duMethodTable.setModel(duMethodModel);
        duMethodTable.setRowHeight(30);
        duMethodTable.setColumnSelectionAllowed(true);
        duMethodTable.setAutoResizeMode(WebTable.AUTO_RESIZE_OFF);
        duMethodModel.initColumnSizes(duMethodTable);
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
        invokeColumn.setCellRenderer(new TableCellButtonRenderer(msg("home.button.test")));
        invokeColumn.setCellEditor(new TableCellButtonEditor(msg("home.button.test"), new ButtonClickCallback() {
            @Override
            public void callback(Object value) {
                if (value == null) {
                    return;
                }
                new InvokeView((DuMethod) value).init();
            }
        }));

    }

    public void setServerInfo(String serverInfo) {
        connServerLabel.setText(serverInfo);
    }

    public void refreshServices(List<DuService> services) {
        List<DuService> serviceVos = new ArrayList<>();
        for (DuService service : services) {
            serviceVos.add(service.clone());
        }
        duServiceModel.setData(serviceVos);
    }

    public void refreshMethods(List<DuMethod> methods) {
        List<DuMethod> methodVos = new ArrayList<>();
        for (DuMethod method : methods) {
            methodVos.add(method.clone());
        }
        duMethodModel.setData(methodVos);
    }
}
