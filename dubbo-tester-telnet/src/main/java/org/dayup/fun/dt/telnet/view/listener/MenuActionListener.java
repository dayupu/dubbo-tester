package org.dayup.fun.dt.telnet.view.listener;

import org.dayup.fun.dt.telnet.view.component.server.NewView;
import org.dayup.fun.dt.telnet.supplier.enums.MenuAction;
import org.dayup.fun.dt.telnet.view.HomePresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {


    private MenuAction action;
    private HomePresenter homePresenter;

    public MenuActionListener(MenuAction action, HomePresenter homePresenter) {
        this.action = action;
        this.homePresenter = homePresenter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action) {
            case SERVER_NEW:
                new NewView(homePresenter).init();
                break;
        }
    }
}
