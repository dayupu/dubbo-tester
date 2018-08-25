package org.dayup.whale.expert.component;

import com.alee.laf.menu.WebMenu;
import org.dayup.whale.expert.skin.Colors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KaMenu extends WebMenu {

    public KaMenu(String name) {
        super(name);
        setForeground(Color.white);

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println(111);
            }
        });
    }

}
