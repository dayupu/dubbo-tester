package org.dayup.fun.dt.telnet.supplier.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/21
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T toBean(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
