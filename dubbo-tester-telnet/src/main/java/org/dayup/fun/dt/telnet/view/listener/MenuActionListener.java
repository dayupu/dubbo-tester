package org.dayup.fun.dt.telnet.view.listener;

import org.dayup.fun.dt.telnet.view.NewConnView;
import org.dayup.fun.dt.telnet.view.enums.MenuAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class MenuActionListener implements ActionListener {


    private MenuAction action;
    private EventListener listener;

    public MenuActionListener(MenuAction action, EventListener listener) {
        this.action = action;
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action) {
            case NEW_CONN:
                NewConnView connView = new NewConnView();
                connView.addListener(listener);
                connView.init();
                break;
        }
    }
}
