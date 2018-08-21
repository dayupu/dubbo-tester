package org.dayup.fun.dt.telnet.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.dayup.fun.dt.telnet.supplier.cache.IconsCache;
import org.dayup.fun.dt.telnet.supplier.config.Configs;
import org.dayup.fun.dt.telnet.supplier.utils.Messages;

public abstract class BaseView {

    private static boolean already = false;
    private static JFrame window;
    private static Configs configs = Configs.load();
    private static Messages message = Messages.getInstance();
    private static IconsCache iconsCache = IconsCache.getInstance();

    public void init() {
        setLayout();
        addListeners();
    }

    protected JFrame getWindow() {
        return window;
    }

    protected void initWindow(JFrame frame) {
        if (already) {
            return;
        }
        window = frame;
        already = true;
    }

    protected abstract void setLayout();

    protected abstract void addListeners();

    protected String msg(String key, String... params) {
        return message.get(key, params);
    }

    protected Configs configs() {
        return configs;
    }

    public ImageIcon loadIcon(final String path) {
        return loadIcon("default", path);
    }

    public ImageIcon loadIcon(String skin, final String path) {
        final String key = skin + ":" + path;
        if (!iconsCache.containsKey(key)) {
            iconsCache.put(key, path);
        }
        return iconsCache.get(key);
    }

}
