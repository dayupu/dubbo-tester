package org.dayup.fish.utils;

import org.dayup.fish.support.FishMessage;

public class Messages {

    public static String msg(String key, Object... params) {
        return FishMessage.getInstance().getMessage(key, params);
    }
}
