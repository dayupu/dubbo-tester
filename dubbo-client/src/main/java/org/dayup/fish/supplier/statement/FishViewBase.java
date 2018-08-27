package org.dayup.fish.supplier.statement;

import org.dayup.fish.utils.Messages;

public abstract class FishViewBase implements IFishView {

    protected String msg(String key, Object... params) {
        return Messages.msg(key, params);
    }

    public void build() {
        this.layout();
        this.listener();
        this.show();
    }

    protected void show() {

    }

    public void bindControls(IControl... controls) {

    }
}
