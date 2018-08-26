package org.dayup.fish.expertise;

import com.alee.laf.rootpane.WebFrame;

public class FishUIManager {

    private static WebFrame container;

    public static void setContainer(WebFrame frame) {
        if (container == null) {
            container = frame;
        }
    }

    public static WebFrame getContainer() {
        return container;
    }

}
