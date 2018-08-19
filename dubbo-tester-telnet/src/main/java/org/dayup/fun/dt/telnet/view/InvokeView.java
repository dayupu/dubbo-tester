package org.dayup.fun.dt.telnet.view;

import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.text.WebTextPane;
import org.dayup.fun.dt.telnet.client.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.dto.DubboMethod;
import org.dayup.fun.dt.telnet.view.service.TelnetService;

import javax.swing.text.StyledDocument;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvokeView extends AbstractView {

    private int width = 650;
    private int height = 500;
    private WebDialog dialog;
    private DubboMethod method;
    private WebButton invokeBtn;
    private WebTextPane paramText;
    private WebTextPane resultText;
    private TelnetService telnetService;

    public InvokeView(DubboMethod method, TelnetService telnetService) {
        this.method = method;
        this.telnetService = telnetService;
    }

    @Override
    protected void setLayout() {
        dialog = new WebDialog(getWindow(), true);
        dialog.setTitle(msg("invoke.title"));
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(getWindow());

        // Split
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, buildHeader(), buildContent());
        splitPane.setMargin(5);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(40);
        splitPane.setContinuousLayout(false);
        dialog.getContentPane().add(splitPane);

    }

    @Override
    protected void addListeners() {
        invokeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String result = telnetService.invoke(method, paramText.getText());
                    resultText.setText(result);
                } catch (TelnetException e1) {
                    e1.printStackTrace();
                }
            }
        });
        dialog.setVisible(true);
    }

    private WebPanel buildHeader() {
        // Top part content
        WebPanel panel = new WebPanel(new BorderLayout());
        WebHotkeyLabel apiLabel = new WebHotkeyLabel();
        StringBuilder builder = new StringBuilder();

        builder.append(method.getApiClass().substring(method.getApiClass().lastIndexOf(".") + 1));
        builder.append(" -> ");
        builder.append(method.getMethodName());
        builder.append("(");
        boolean isFirst = true;
        for (String param : method.getInParams()) {
            if (!isFirst) {
                builder.append(", ");
            }
            builder.append(param);
            isFirst = false;
        }
        builder.append(")");
        apiLabel.setText(builder.toString());

        invokeBtn = new WebButton(msg("invoke.button.test"));
        panel.add(apiLabel, BorderLayout.WEST);
        panel.add(invokeBtn, BorderLayout.EAST);
        return new WebPanel(false, panel);

    }

    private WebSplitPane buildContent() {

        // Left part content
        paramText = new WebTextPane();
        paramText.setText(msg("invoke.textarea.param"));
        paramText.setMargin(new Insets(5, 5, 5, 5));
        WebScrollPane leftPanel = new WebScrollPane(paramText);

        // Right part content
        resultText = new WebTextPane();
        resultText.setText(msg("invoke.textarea.result"));
        WebScrollPane rightPanel = new WebScrollPane(resultText);

        // Split
        WebSplitPane splitPane = new WebSplitPane(WebSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(250);
        splitPane.setContinuousLayout(true);

        return splitPane;
    }
}
