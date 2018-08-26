package org.dayup.fish.view.provider;

import org.dayup.fish.expertise.statement.IControl;

public class ProviderAddControl implements IControl {

    private ProviderAddView view;

    public ProviderAddControl(ProviderAddView view) {
        this.view = view;
    }

    public void addProvider(String ip, Integer port) {
        System.out.println(ip);
        System.out.println(port);
    }
}
