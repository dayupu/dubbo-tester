package org.dayup.fish.expertise.custom.panel;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.panel.WebPanel;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.FlowLayout;

public class DialogPanel extends WebPanel {

    private WebPanel top;
    private WebPanel center;
    private WebPanel bottom;
    private Color lineColor = new Color(192, 192, 192);
    private Color backgroundColor = new Color(245, 245, 245);

    public DialogPanel() {
        initLayout();
    }

    public DialogPanel(WebPanel topPanel, WebPanel centerPanel, WebPanel bottomPanel) {
        this.top = topPanel;
        this.center = centerPanel;
        this.bottom = bottomPanel;
        initLayout();
    }

    private void initLayout() {
        if (this.top == null) {
            this.top = new WebPanel();
        }
        if (this.center == null) {
            this.center = new WebPanel();
        }
        if (this.bottom == null) {
            this.bottom = new WebPanel();
        }
        designStyle();
        GroupPanel groupPanel = new GroupPanel(GroupingType.fillMiddle, 0, false, this.top, this.center, this.bottom);
        this.add(groupPanel);
    }


    private void designStyle() {
        this.setOpaque(true);
        this.setBackground(backgroundColor);
        this.top.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.top.setMargin(5);
        this.top.setOpaque(false);
        this.top.setMinimumHeight(40);
        this.top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lineColor));
        this.center.setOpaque(true);
        this.center.setMargin(10);
        this.center.setMinimumHeight(50);
        this.center.setLayout(new FlowLayout());
        this.bottom.setOpaque(false);
        this.bottom.setMargin(5);
        this.bottom.setMinimumHeight(30);
        this.bottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, lineColor));
        this.bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    public WebPanel getTop() {
        return top;
    }

    public void setTop(WebPanel top) {
        this.top = top;
    }

    public WebPanel getCenter() {
        return center;
    }

    public void setCenter(WebPanel center) {
        this.center = center;
    }

    public WebPanel getBottom() {
        return bottom;
    }

    public void setBottom(WebPanel bottom) {
        this.bottom = bottom;
    }
}
