package org.dayup.fish.view.assembly;

import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;

public class FishMenuBar extends WebMenuBar {


    public FishMenuBar() {
        createMenus();
    }

    public void createMenus() {
        WebMenu menu = new WebMenu("xx");
        add(menu);
    }

}
