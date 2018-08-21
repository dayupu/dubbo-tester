package org.dayup.fun.dt.telnet.supplier.config.prop;

import org.dayup.fun.dt.telnet.supplier.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Properties;

public class PropHandler {

    private final static String USER_PATH = System.getProperties().getProperty("user.home");
    private final static String CONFIG_DIR = "/.XTelnet/";
    private final static String UTF8 = "utf-8";

    public static <T> T parse(Class<T> clazz, boolean isClassPath) {
        try {
            T target = clazz.newInstance();
            PropFile propFile = clazz.getDeclaredAnnotation(PropFile.class);
            if (propFile == null) {
                return target;
            }
            Properties properties;
            if (isClassPath) {
                properties = readWithClassPath(propFile.value());
            } else {
                properties = readWithUserPath(propFile.value());
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                PropKey propKey = field.getAnnotation(PropKey.class);
                if (propKey == null) {
                    continue;
                }
                field.setAccessible(true);
                Type type = field.getGenericType();
                String propValue = properties.getProperty(propKey.value());
                if (type == String.class) {
                    field.set(target, propValue);
                } else if (type == Integer.class) {
                    field.set(target, Integer.valueOf(propValue));
                } else if (type == Long.class) {
                    field.set(target, Long.valueOf(propValue));
                } else {
                    throw new IllegalArgumentException("Not support type of " + field.getName());
                }
            }
            return target;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void store(Object target) {
        PropFile propFile = target.getClass().getDeclaredAnnotation(PropFile.class);
        if (propFile == null) {
            throw new IllegalArgumentException("Not support store properties of " + target.getClass());
        }
        OutputStream outputStream = null;
        try {
            File file = new File(FileUtils.joinPath(USER_PATH, CONFIG_DIR, propFile.value()));
            outputStream = new FileOutputStream(file);
            Properties properties = new Properties();
            Field[] fields = target.getClass().getDeclaredFields();
            for (Field field : fields) {
                PropKey propKey = field.getAnnotation(PropKey.class);
                if (propKey == null) {
                    continue;
                }
                field.setAccessible(true);
                properties.put(propKey.value(), String.valueOf(field.get(target)));
            }
            properties.store(outputStream, "Modify configuration");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    }

    private static Properties readWithClassPath(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(FileUtils.class.getResourceAsStream(fileName), UTF8));
        return properties;
    }

    private static Properties readWithUserPath(String fileName) throws IOException {
        checkDir();
        Properties properties = new Properties();
        File file = new File(FileUtils.joinPath(USER_PATH, CONFIG_DIR, fileName));
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
                properties = readWithClassPath(fileName);
                outputStream = new FileOutputStream(file);
                properties.store(outputStream, "Init configuration");
            } else {
                inputStream = new FileInputStream(file);
                properties.load(new InputStreamReader(inputStream, UTF8));
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }

    private static void checkDir() {
        File file = new File(USER_PATH, CONFIG_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
