package org.dayup.fun.dt.telnet.supplier.dubbo;

import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TelnetPool {


    private static TelnetPool telnetPool = new TelnetPool();
    private final static int MAX_SIZE = 20;
    private final static int DEFAULT_CONNECT_TIMEOUT = 5000;
    private final static int DEFAULT_SOCKET_TIMEOUT = 3000000;
    private Map<String, TelnetConnect> connCache = new ConcurrentHashMap<>();

    private TelnetPool() {

    }

    public static TelnetConnect openConnect(String ip, int port) throws TelnetException {
        return telnetPool.getConnect(ip, port);
    }

    private TelnetConnect getConnect(String ip, int port) throws TelnetException {
        try {
            String cacheKey = ip + ":" + port;
            if (connCache.size() > MAX_SIZE) {
                connCache.clear();
            }
            TelnetConnect client;
            if (!connCache.containsKey(cacheKey)) {
                client = new TelnetConnect(ip, port);
                client.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
                client.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
                client.open();
                connCache.put(cacheKey, client);
            }
            client = connCache.get(cacheKey);
            if (!client.isConnected()) {
                client.close();
                client.open();
            }
            return client;
        } catch (Exception e) {
            throw new TelnetException("Can't get the telnet connect", e);
        }
    }
}
