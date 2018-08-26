package org.dayup.fish.view.assembly;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import org.dayup.fish.expertise.custom.enums.FishImage;
import org.dayup.fish.expertise.style.FishColor;
import org.dayup.fish.utils.Messages;
import org.dayup.fish.view.provider.ProviderAddView;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FishTopPanel extends WebPanel {

    private static final int CONT_HEIGHT = 40;

    public FishTopPanel() {
        this.setPreferredHeight(CONT_HEIGHT);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(FishColor.LIGHT);
        this.initLayout();
    }


    private void initLayout() {
        GroupPanel group = new GroupPanel(2);
        WebButton plusBtn = new WebButton(msg("cpt.main.top.button.add_provider"), FishImage.BTN_ADD.image());
        plusBtn.setFocusable(false);
        plusBtn.addActionListener(new BtnActionListener(BtnAction.PLUS_PROVIDER));
        group.add(plusBtn);
        this.add(group);
    }

    public String msg(String key) {
        return Messages.msg(key);
    }


    enum BtnAction {
        PLUS_PROVIDER;
    }

    class BtnActionListener implements ActionListener {

        private BtnAction action;

        public BtnActionListener(BtnAction action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (action) {
                case PLUS_PROVIDER:
                    new ProviderAddView().build();
            }
        }
    }

}
