package org.dayup.whale.expert;

import org.dayup.whale.exceptions.ViewBuildException;
import org.dayup.whale.expert.assembly.IBasicView;

public class WhaleBuilder {

    public static <T extends IBasicView> T buildView(Class<T> clazz) {
        try {
            T view = clazz.newInstance();
            view.layout();
            view.listener();
            return view;
        } catch (Exception e) {
            throw new ViewBuildException("View buildView failed of " + clazz.getName());
        }
    }
}
