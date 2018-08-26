package org.dayup.fish.support;

import java.util.regex.Pattern;

public class FishValidator {


    private static Pattern ipV4Regex = Pattern.compile("(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))");
    private static Pattern numberRegex = Pattern.compile("\\d+");

    public static boolean isIPv4(String ip) {
        return ipV4Regex.matcher(ip).matches();
    }

    public static boolean isNumber(String number) {
        return numberRegex.matcher(number).matches();
    }
}
