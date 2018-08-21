package org.dayup.fun.dt.telnet;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.notification.NotificationManager;
import org.dayup.fun.dt.telnet.view.HomeView;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Enumeration;

public class Launcher {


    public static void main(String[] args) throws InterruptedException {
        EventQueue.invokeLater(() -> {
            try {
                InitGlobalFont();
                UIManager.setLookAndFeel(new WebLookAndFeel());
                WebLookAndFeel.setDecorateDialogs(true);
                WebLookAndFeel.setDecorateFrames(true);
                NotificationManager.setLocation(4);
                NotificationManager.setMargin(20);
                new HomeView().init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private static void InitGlobalFont() {
        Font font = new Font("宋体", Font.PLAIN, 14);
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }


}
