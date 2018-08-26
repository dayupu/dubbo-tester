package org.dayup.fish.expertise.custom.panel;

import com.alee.laf.label.WebLabel;

import java.awt.Color;

public class TipLabel extends WebLabel {

    private Color textColor = new Color(77, 77, 77);

    public TipLabel(String text) {
        this.initStyle();
        this.setText(text);
    }


    private void initStyle() {
        this.setFontSize(12);
        this.setForeground(textColor);
    }
}
