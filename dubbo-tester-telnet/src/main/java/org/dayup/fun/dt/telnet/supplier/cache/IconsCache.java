package org.dayup.fun.dt.telnet.supplier.cache;

import javax.swing.ImageIcon;

public class IconsCache extends Cache<String, ImageIcon> {

    private static IconsCache iconsCache = new IconsCache();

    private IconsCache() {

    }

    public static IconsCache getInstance() {
        return iconsCache;
    }

    public ImageIcon put(String key, String path) {
        return put(key, new ImageIcon(IconsCache.class.getResource("/icons/" + path)));
    }

}
