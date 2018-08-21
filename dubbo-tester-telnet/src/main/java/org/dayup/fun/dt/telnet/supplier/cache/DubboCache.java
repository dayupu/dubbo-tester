package org.dayup.fun.dt.telnet.supplier.cache;

import org.dayup.fun.dt.telnet.bean.DuService;

public class DubboCache extends Cache<String, DuService> {

    private static DubboCache dubboCache = new DubboCache();

    private DubboCache() {

    }

    public static DubboCache getInstance() {
        return dubboCache;
    }

}
