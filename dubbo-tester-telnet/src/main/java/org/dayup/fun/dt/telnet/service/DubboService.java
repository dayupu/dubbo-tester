package org.dayup.fun.dt.telnet.service;

import org.dayup.fun.dt.telnet.bean.DuConnect;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.bean.DuService;
import org.dayup.fun.dt.telnet.supplier.cache.DubboCache;
import org.dayup.fun.dt.telnet.supplier.dubbo.DubboClient;
import org.dayup.fun.dt.telnet.supplier.dubbo.TelnetConnect;
import org.dayup.fun.dt.telnet.supplier.dubbo.TelnetPool;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.DuCallException;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.supplier.dubbo.parser.DubboParser;
import org.dayup.fun.dt.telnet.supplier.utils.JsonUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class DubboService {

    private final static DubboService dubboService = new DubboService();

    private DubboCache dubboCache = DubboCache.getInstance();

    private DubboService() {
    }

    public static DubboService getInstance() {
        return dubboService;
    }

    public String invoke(DuMethod method, String json) {
        try {
            DuService service = DubboCache.getInstance().get(method.getServiceKey());
            Object result = DubboClient.call(service, method, json);
            return JsonUtils.toJson(result);
        } catch (DuCallException e) {
            return "【Request Error】" + System.lineSeparator() + e.getMessage();
        } catch (Exception e) {
            return "【Service Error】" + System.lineSeparator() + e.getMessage();
        }
    }

    public List<DuMethod> findMethods(DuService service) {
        DuService cachedService = dubboCache.get(service.key());
        if (cachedService.getMethods() != null) {
            return cachedService.getMethods();
        }
        return null;
    }

    public List<DuService> findServices(DuConnect connect) throws TelnetException {
        TelnetConnect client = null;
        try {
            client = TelnetPool.openConnect(connect.ip(), connect.port());
            String response = client.sendCmd("ls -l");
            List<DuService> services = DubboParser.toDuServices(connect, response);
            dubboCache.clear();
            List<DuMethod> serviceMethods;
            for (DuService service : services) {
                String methodResponse = client.sendCmd("ls -l " + service.getInterfaceClass());
                serviceMethods = DubboParser.toDuMethods(service.key(), methodResponse);
                service.setMethods(serviceMethods);
                dubboCache.put(service.key(), service);
            }
            return Collections.unmodifiableList(services);
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                throw new TelnetException("Close connect occurred error", e);
            }
        }
    }
}
