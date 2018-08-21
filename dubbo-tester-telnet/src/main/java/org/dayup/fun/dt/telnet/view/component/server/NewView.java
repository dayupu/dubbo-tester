package org.dayup.fun.dt.telnet.view.component.server;

import com.alee.extended.progress.WebProgressOverlay;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebDialog;
import com.alee.managers.notification.NotificationManager;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.view.BaseView;
import org.dayup.fun.dt.telnet.view.HomePresenter;

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

public class NewView extends BaseView {

    private HomePresenter homePresenter;

    private int width = 400;
    private int height = 250;
    private JDialog dialog;
    private JTextField ipText;
    private JTextField portText;
    private JButton connBtn;
    private WebProgressOverlay connProgressOverlay;

    public NewView(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    @Override
    protected void setLayout() {
        dialog = new WebDialog(getWindow(), true);
        dialog.setTitle(msg("server.new.title"));
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
                String ip = ipText.getText().trim();
                Integer port = Integer.valueOf(portText.getText().trim());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            homePresenter.dubboServices(ip, port);
                            configs().setLastServer(ip, String.valueOf(port));
                            dialog.setVisible(false);
                        } catch (TelnetException e) {
                            NotificationManager.showNotification(msg("message.connect.failed"));
                        } finally {
                            connectDone();
                        }
                    }
                }).start();
            }
        });

        dialog.setVisible(true);
    }

    private void connectStart() {
        connBtn.setEnabled(false);
        connProgressOverlay.setShowLoad(true);
        connBtn.setText(msg("server.new.button.connecting"));
    }

    private void connectDone() {
        connBtn.setEnabled(true);
        connBtn.setText(msg("server.new.button.connect"));
        connProgressOverlay.setShowLoad(false);
    }

    private void addForm() {
        JLabel ipLabel = new JLabel(msg("server.new.label.ip"));
        ipText = new JTextField(configs().getLastServerIp());
        JLabel portLabel = new JLabel(msg("server.new.label.port"));
        portText = new JTextField(configs().getLastServerPort());
        connBtn = new WebButton(msg("server.new.button.connect"));
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

}
