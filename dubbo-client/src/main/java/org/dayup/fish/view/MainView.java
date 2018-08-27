package org.dayup.fish.view;

import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.laf.rootpane.WebFrame;
import org.dayup.fish.supplier.FishUIManager;
import org.dayup.fish.supplier.statement.FishViewBase;
import org.dayup.fish.view.assembly.FishBodyPanel;
import org.dayup.fish.view.assembly.FishTopPanel;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends FishViewBase {

    private WebFrame frame;

    @Override
    public void layout() {
        frame = new WebFrame(msg("app.name"));
        frame.pack();
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        // 设置菜单
//        frame.setJMenuBar(new FishMenuBar());
        // 设置内容
        GroupPanel content = new GroupPanel(GroupingType.fillLast, 5, false, new FishTopPanel(), new FishBodyPanel());
        frame.add(content);
        frame.getContentPane().setBackground(Color.WHITE);
        FishUIManager.setContainer(frame);
    }

    @Override
    public void listener() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    @Override
    protected void show() {
        frame.setVisible(true);
    }
}
