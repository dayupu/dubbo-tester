package org.dayup.fun.dt.telnet.supplier.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

public class Messages {

    private static Messages messages = new Messages();
    private Properties properties;
    private Messages() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(Messages.class.getResourceAsStream("/message.properties"), "utf-8"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found for [message.properties]", e);
        }
    }

    public static Messages getInstance() {
        return messages;
    }

    public String get(String key, String... params) {
        String value = properties.getProperty(key);
        if (value == null) {
            return key;
        }
        if (params == null) {
            return value;
        }
        return MessageFormat.format(value, params);
    }

}
