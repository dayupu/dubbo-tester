package org.dayup.whale.expert.component;

import com.alee.laf.menu.WebMenuBar;
import org.dayup.whale.expert.skin.Colors;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class KaMenuBar extends WebMenuBar {


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Colors.BLACK);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
