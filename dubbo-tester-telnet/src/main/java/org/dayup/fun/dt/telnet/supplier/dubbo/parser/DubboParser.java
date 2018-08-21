package org.dayup.fun.dt.telnet.supplier.dubbo.parser;

import org.dayup.fun.dt.telnet.bean.DuConnect;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.bean.DuService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/20
 */
public class DubboParser {

    private static String LINE = System.lineSeparator();

    private static Pattern serviceRegex = Pattern.compile("(.+)\\s+->\\s+dubbo.+\\?(.+)");
    private static Pattern methodRegex = Pattern.compile("(.+)\\s+(.+)\\((.*)\\)");

    public static List<DuService> toDuServices(DuConnect connect, String telnetStr) {
        List<DuService> duServices = new ArrayList<>();
        if (isBlank(telnetStr)) {
            return duServices;
        }
        Matcher matcher;
        DuService duService;
        for (String str : telnetStr.split(LINE)) {
            str = str.trim();
            matcher = serviceRegex.matcher(str);
            if (!matcher.find()) {
                continue;
            }
            String interfaceClass = matcher.group(1);
            String interfaceInfo = matcher.group(2);
            duService = new DuService(connect);
            duService.setInterfaceClass(interfaceClass);
            String[] args = interfaceInfo.split("&");
            for (String info : args) {
                String[] dict = info.split("=");
                if (dict.length != 2) {
                    continue;
                }
                if ("application".equals(dict[0])) {
                    duService.setApplication(dict[1]);
                } else if ("default.timeout".equals(dict[0])) {
                    duService.setDefaultTimeout(dict[1]);
                } else if ("dubbo".equals(dict[0])) {
                    duService.setDubboVersion(dict[1]);
                } else if ("generic".equals(dict[0])) {
                    duService.setGeneric(Boolean.valueOf(dict[1]));
                } else if ("methods".equals(dict[0])) {
                    duService.setMethodStr(dict[1]);
                } else if ("pid".equals(dict[0])) {
                    duService.setPid(dict[1]);
                } else if ("revision".equals(dict[0])) {
                    duService.setRevision(dict[1]);
                } else if ("side".equals(dict[0])) {
                    duService.setSide(dict[1]);
                } else if ("version".equals(dict[0])) {
                    duService.setVersion(dict[1]);
                }
            }
            duServices.add(duService);
        }
        return duServices;
    }

    public static List<DuMethod> toDuMethods(String serviceKey, String telnetStr) {

        Matcher matcher;
        DuMethod duMethod;
        List<DuMethod> duMethods = new ArrayList<>();
        if (isBlank(telnetStr)) {
            return duMethods;
        }
        for (String str : telnetStr.split(LINE)) {
            str = str.trim();
            matcher = methodRegex.matcher(str);
            if (!matcher.find()) {
                continue;
            }
            duMethod = new DuMethod(serviceKey);
            duMethod.setReturnType(matcher.group(1));
            duMethod.setName(matcher.group(2));
            String params = matcher.group(3);
            if (!isBlank(params)) {
                duMethod.setParamTypes(Arrays.asList(params.split(",")));
            }
            duMethods.add(duMethod);
        }
        return duMethods;
    }


    private static boolean isBlank(String str) {
        return str == null ? true : "".equals(str.trim());
    }
}
