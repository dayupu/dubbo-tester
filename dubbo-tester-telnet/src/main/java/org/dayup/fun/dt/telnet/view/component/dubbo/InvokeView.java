package org.dayup.fun.dt.telnet.view.component.dubbo;

import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.text.WebTextPane;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.view.BaseView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InvokeView extends BaseView {

    private DuMethod method;
    private InvokePresenter invokePresenter;

    private WebDialog dialog;
    private WebButton invokeBtn;
    private WebTextPane paramText;
    private WebTextPane resultText;

    public InvokeView(DuMethod method) {
        this.method = method;
        this.invokePresenter = new InvokePresenter(this);
    }

    @Override
    protected void setLayout() {
        dialog = new WebDialog(getWindow(), true);
        dialog.pack();
        dialog.setTitle(msg("dubbo.invoke.title"));
        dialog.setSize(new Dimension(configs().getInvokeWidth(), configs().getInvokeHeight()));
        dialog.setLocationRelativeTo(getWindow());

        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, buildHeader(), buildContent());
        splitPane.setMargin(5);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(90);
        splitPane.setContinuousLayout(false);
        dialog.getContentPane().add(splitPane);
    }

    @Override
    protected void addListeners() {
        invokeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultText.setText(invokePresenter.invoke(method, paramText.getText()));
            }
        });

        dialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension dimension = e.getComponent().getSize();
                configs().setInvokeSize(dimension.width, dimension.height);
            }
        });

        dialog.setVisible(true);
    }

    private WebPanel buildHeader() {
        WebPanel panel = new WebPanel(new BorderLayout());
        WebHotkeyLabel apiLabel = new WebHotkeyLabel();
        apiLabel.setText(method.simpleInfo());
        invokeBtn = new WebButton(msg("dubbo.invoke.button.test"));
        WebPanel btnPanel = new WebPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setMinimumHeight(40);
        btnPanel.add(invokeBtn);
        WebScrollPane apiPane = new WebScrollPane(apiLabel, false, false);
        apiPane.setMinimumHeight(45);
        panel.add(apiPane, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return new WebPanel(false, panel);

    }

    private WebSplitPane buildContent() {

        // Left part getWindow
        paramText = new WebTextPane();
        paramText.setText(invokePresenter.methodParams(method));
        paramText.setMargin(new Insets(5, 5, 5, 5));
        WebScrollPane leftPanel = new WebScrollPane(paramText);

        // Right part getWindow
        resultText = new WebTextPane();
        resultText.setText("");
        WebScrollPane rightPanel = new WebScrollPane(resultText);

        // Split
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        splitPane.setContinuousLayout(true);

        return splitPane;
    }

}
