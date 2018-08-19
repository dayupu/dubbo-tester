package org.dayup.fun.dt.telnet.view;

import javax.swing.JPanel;

public class BodyView extends AbstractView {


    private JPanel body;


    @Override
    protected void setLayout() {
        body = new JPanel();
    }

    @Override
    protected void addListeners() {

    }


    public JPanel getBody() {
        init();
        return body;
    }
}
