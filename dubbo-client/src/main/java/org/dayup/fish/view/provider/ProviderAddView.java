package org.dayup.fish.view.provider;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import com.alee.laf.text.WebTextField;
import com.alee.managers.notification.NotificationManager;
import org.dayup.fish.expertise.FishUIManager;
import org.dayup.fish.expertise.custom.enums.FishImage;
import org.dayup.fish.expertise.custom.panel.DialogPanel;
import org.dayup.fish.expertise.custom.panel.PlusPanel;
import org.dayup.fish.expertise.custom.panel.TipLabel;
import org.dayup.fish.expertise.statement.FishViewBase;
import org.dayup.fish.support.FishValidator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProviderAddView extends FishViewBase {

    private static final int width = 400;
    private static final int height = 250;
    private static final int LABEL_WIDTH = 50;
    private static final int TEXT_WIDTH = 220;

    private WebDialog dialog;
    private WebTextField ipText;
    private WebTextField portText;
    private WebButton addBtn;

    private ProviderAddControl paControl;

    public ProviderAddView() {
        paControl = new ProviderAddControl(this);
    }

    @Override
    public void layout() {
        dialog = new WebDialog();
        dialog.setTitleComponent(null);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(FishUIManager.getContainer());
        dialog.setModal(true);
        dialog.setResizable(false);
        this.createBody();
    }

    @Override
    public void listener() {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = ipText.getText().trim();
                String port = portText.getText().trim();

                if (!FishValidator.isIPv4(ip) || !FishValidator.isNumber(port)) {
                    NotificationManager.showNotification(msg("msg.info.ip_or_port_invalid"));
                    return;
                }
                paControl.addProvider(ip, Integer.valueOf(port));
            }
        });
    }

    @Override
    protected void show() {
        dialog.setVisible(true);
    }

    private void createBody() {

        PlusPanel topPanel = new PlusPanel();
        topPanel.add(new TipLabel(msg("cpt.provider.add.label.desc")));

        WebPanel centerPanel = new WebPanel();
        WebLabel ipLabel = new WebLabel(msg("cpt.provider.add.label.ip"));
        ipLabel.setPreferredWidth(LABEL_WIDTH);
        ipLabel.setBoldFont(true);
        ipText = new WebTextField();
        ipText.setPreferredWidth(TEXT_WIDTH);

        WebLabel portLabel = new WebLabel(msg("cpt.provider.add.label.port"));
        portLabel.setBoldFont(true);
        portLabel.setPreferredWidth(LABEL_WIDTH);
        portText = new WebTextField();
        portText.setPreferredWidth(TEXT_WIDTH);
        centerPanel.add(new GroupPanel(5, ipLabel, ipText));
        centerPanel.add(new GroupPanel(5, portLabel, portText));

        WebPanel bottomPanel = new WebPanel();
        addBtn = new WebButton(msg("cpt.provider.add.button.add"), FishImage.BTN_ADD.image());
        bottomPanel.add(addBtn);

        dialog.add(new DialogPanel(topPanel, centerPanel, bottomPanel));
    }
}
