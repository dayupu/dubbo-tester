package org.dayup.fun.dt.telnet.view;

import com.alee.extended.progress.WebProgressOverlay;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.managers.notification.NotificationManager;
import org.dayup.fun.dt.telnet.client.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.view.listener.TelnetListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class NewConnView extends AbstractView {

    private JDialog dialog;
    private JTextField ipText;
    private JTextField portText;
    private JButton connBtn;
    private WebProgressOverlay connProgressOverlay;

    private List<EventListener> listeners = new ArrayList<>();

    private int width = 300;
    private int height = 180;

    @Override
    protected void setLayout() {
        /*显示对话框*/
        dialog = new WebDialog(getWindow(), true);
        dialog.setTitle(msg("new_connect.title"));
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(getWindow());
        addForm();
    }

    @Override
    protected void addListeners() {

        connBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent target) {
                connectStart();
                String ip = ipText.getText();
                Integer port = Integer.valueOf(portText.getText());
                for (EventListener listener : listeners) {
                    if (listener instanceof TelnetListener) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ((TelnetListener) listener).telnetEvent(ip, port);
                                    dialog.setVisible(false);
                                } catch (TelnetException e) {
                                    NotificationManager.setMargin(50);
                                    NotificationManager.showNotification(msg("message.connect.failed"));
                                } finally {
                                    connectDone();
                                }
                            }
                        }).start();
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    private void connectStart() {
        connBtn.setEnabled(false);
        connProgressOverlay.setShowLoad(true);
        connBtn.setText(msg("new_connect.button.connecting"));
    }

    private void connectDone() {
        connBtn.setEnabled(true);
        connBtn.setText(msg("new_connect.button.connect"));
        connProgressOverlay.setShowLoad(false);
    }

    private void addForm() {
        JLabel ipLabel = new JLabel(msg("new_connect.label.ip"));
        ipText = new JTextField("192.168.100.106");
        JLabel portLabel = new JLabel(msg("new_connect.label.port"));
        portText = new JTextField("20000");
        connBtn = new WebButton(msg("new_connect.button.connect"));
        connBtn.setPreferredSize(new Dimension(100, 30));
        connProgressOverlay = new WebProgressOverlay();
        connProgressOverlay.setConsumeEvents(false);
        connProgressOverlay.setComponent(connBtn);

        adjustLabel(ipLabel, portLabel);
        adjustTextField(ipText, portText);

        Box ipDiv = Box.createHorizontalBox();
        ipDiv.add(ipLabel);
        ipDiv.add(ipText);
        Box portDiv = Box.createHorizontalBox();
        portDiv.add(portLabel);
        portDiv.add(portText);
        Box btnDiv = Box.createHorizontalBox();
        btnDiv.add(new JPanel().add(connProgressOverlay));

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        box.add(ipDiv);
        box.add(Box.createVerticalStrut(10));
        box.add(portDiv);
        box.add(Box.createVerticalStrut(30));
        box.add(btnDiv);

        Container body = dialog.getContentPane();
        body.setLayout(new FlowLayout(FlowLayout.CENTER));
        body.add(box);
    }

    private void adjustLabel(JLabel... labels) {
        for (JLabel label : labels) {
            label.setPreferredSize(new Dimension(60, 30));
        }
    }

    private void adjustTextField(JTextField... textFields) {
        for (JTextField textField : textFields) {
            textField.setPreferredSize(new Dimension(200, 30));
        }
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }
}
