package org.dayup.whale.view;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.menu.MenuBarStyle;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.splitpane.WebSplitPane;
import org.dayup.whale.expert.ViewManager;
import org.dayup.whale.expert.assembly.IView;
import org.dayup.whale.expert.component.KaMenu;
import org.dayup.whale.expert.component.KaMenuBar;
import org.dayup.whale.expert.skin.Colors;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainView extends ViewManager implements IView {


    WebSplitPane leftBody;
    WebSplitPane mainBody;

    @Override
    public void layout() {
        WebFrame main = new WebFrame();
        main.pack();
        main.setSize(1000, 500);
        main.setLocationRelativeTo(null);
        initContainer(main);
        // 设置菜单
        designMenu();
        // 设置内容
        GroupPanel content = new GroupPanel(GroupingType.fillLast, 10, false, designHeader(), mainBody());
        getContainer().add(content);
    }


    private void designMenu() {
        KaMenuBar menuBar = new KaMenuBar();
        KaMenu file = new KaMenu("File");
        menuBar.add(file);
        WebMenuItem item1 = new WebMenuItem("xx");
        item1.setBackground(Colors.BLACK);
        file.add(item1);
        menuBar.setForeground(Colors.BLACK);
        menuBar.setBackground(Colors.BLACK);
        getContainer().setJMenuBar(menuBar);
    }

    private Component designHeader() {
        WebPanel panel = new WebPanel();
        panel.setPreferredHeight(50);
        return panel;
    }

    private Component mainBody() {
        Component left = leftBody();
        WebPanel right = new WebPanel(true);
        mainBody = new WebSplitPane(WebSplitPane.HORIZONTAL_SPLIT, left, right);
        return mainBody;
    }


    private Component leftBody() {
        WebPanel operation = new WebPanel(true);
        WebPanel explain = new WebPanel(true);
        leftBody = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, operation, explain);
        leftBody.setDividerLocation(0.5d);
        leftBody.setOneTouchExpandable(true);
        return leftBody;
    }


    @Override
    public void listener() {

        getContainer().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mainBody.repaint();
                leftBody.repaint();
            }
        });
    }

    @Override
    public void show() {
        getContainer().setVisible(true);
        mainBody.setDividerLocation(0.3d);
        leftBody.setDividerLocation(0.7d);
    }


}
