package org.dayup.whale.expert;

import com.alee.laf.rootpane.WebFrame;

public class ViewManager {

    private static WebFrame container;

    protected void initContainer(WebFrame frame) {
        if (container == null) {
            container = frame;
        }
    }

    protected static WebFrame getContainer() {
        return container;
    }
}
