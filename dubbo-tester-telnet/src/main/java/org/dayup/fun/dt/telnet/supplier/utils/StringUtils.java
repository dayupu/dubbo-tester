package org.dayup.fun.dt.telnet.supplier.utils;

import java.util.Collection;
import java.util.Collections;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/21
 */
public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null ? true : "str".equals(str.trim());
    }

    public static String join(Collection<String> args, String joinMark) {
        if (args == null || args.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg).append(joinMark);
        }
        builder.setLength(builder.length() - joinMark.length());
        return builder.toString();
    }
}
