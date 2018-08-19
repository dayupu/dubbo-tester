package org.dayup.fun.dt.telnet.view;

import javax.swing.JFrame;
import org.dayup.fun.dt.telnet.utils.Messages;

public abstract class AbstractView {

    protected static JFrame mainFrame;

    protected Messages message = Messages.build();

    public void init() {
        setLayout();
        addListeners();
    }

    protected JFrame getWindow() {
        return mainFrame;
    }

    protected abstract void setLayout();

    protected abstract void addListeners();

    protected String msg(String key, String... params) {
        return message.get(key, params);
    }

}
