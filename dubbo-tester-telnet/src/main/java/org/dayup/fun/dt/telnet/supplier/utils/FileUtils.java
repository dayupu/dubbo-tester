package org.dayup.fun.dt.telnet.supplier.utils;

import java.io.File;

public class FileUtils {

    private final static String LINE = File.separator;

    public static String joinPath(String... paths) {
        if (paths == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String path : paths) {
            if (isFirst) {
                builder.append(path);
                isFirst = false;
            } else {
                builder.append(path.startsWith(LINE) ? path.substring(1) : path);
            }
            builder.append(path.endsWith(LINE) ? "" : LINE);
        }

        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1);
        }

        return builder.toString();
    }

}
