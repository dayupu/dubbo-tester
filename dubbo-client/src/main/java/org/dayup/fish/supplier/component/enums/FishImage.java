package org.dayup.fish.supplier.component.enums;

import org.dayup.fish.support.cache.ImageIconCache;

import javax.swing.ImageIcon;

public enum FishImage {

    TAG("/icons/tag.png"),
    FAVORITE("/icons/favorite.png"),
    BOOK("/icons/BOOK.png"),
    COMMENT("/icons/comment.png"),
    ATTENTION("/icons/attention.png"),
    BTN_ADD("/icons/btn-add.png"),
    ;

    private String icon;

    FishImage(String icon) {
        this.icon = icon;
    }

    public ImageIcon image() {
        return ImageIconCache.instance().getAndPut("FISH-IMAGE-" + this.name(), icon);
    }
}
