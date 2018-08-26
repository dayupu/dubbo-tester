package org.dayup.fish.support;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

public class FishMessage {

    private static FishMessage messages = new FishMessage();
    private Properties properties;
    private FishMessage() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(FishMessage.class.getResourceAsStream("/message.properties"), "utf-8"));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found for [message.properties]", e);
        }
    }

    public static FishMessage getInstance() {
        return messages;
    }

    public String getMessage(String key, Object... params) {
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
