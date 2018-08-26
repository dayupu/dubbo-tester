package org.dayup.fish.support.cache;

import javax.swing.ImageIcon;

public class ImageIconCache extends Cache<String, ImageIcon> {

    private static ImageIconCache imageCache = new ImageIconCache();

    private ImageIconCache() {

    }

    public static ImageIconCache instance() {
        return imageCache;
    }

    public ImageIcon put(String key, String path) {
        return put(key, new ImageIcon(ImageIconCache.class.getResource(path)));
    }


    public ImageIcon getAndPut(String key, String path) {
        if (imageCache.containsKey(key)) {
            return imageCache.get(key);
        }

        ImageIcon icon = new ImageIcon(ImageIconCache.class.getResource(path));
        imageCache.put(key, icon);
        return icon;
    }
}
