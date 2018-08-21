package org.dayup.fun.dt.telnet.supplier.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.dayup.fun.dt.telnet.bean.DuConnect;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.bean.DuService;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.DuCallException;
import org.dayup.fun.dt.telnet.supplier.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/20
 */
public class DubboClient {

    private final static ReferenceConfigCache referenceCache = ReferenceConfigCache.getCache();

    private static ApplicationConfig dubboApp = new ApplicationConfig();

    private static String dubboCallTemplate = "dubbo://%s:%d";

    static {
        dubboApp.setName("tester-application");
    }

    public static Object call(DuService service, DuMethod method, String json) throws DuCallException {
        try {
            Object[] params = new Object[0];
            if (json != null && !"".equals(json.trim())) {
                params = JsonUtils.toBean(json, List.class).toArray();
            }
            if (params.length != method.getParamTypes().size()) {
                throw new DuCallException(String.format("Args.length(%d) != Types.length(%d)",
                        params.length, method.getParamTypes().size()));
            }
            return call(service, method, params);
        } catch (IOException e) {
            throw new DuCallException(e);
        }

    }

    public static Object call(DuService service, DuMethod method, Object... params) {
        return genericService(service).$invoke(method.getName(), method.getParamTypes().toArray(new String[0]), params);
    }

    private static GenericService genericService(DuService service) {
        return genericService(referenceConfig(service));
    }

    private static GenericService genericService(ReferenceConfig<GenericService> config) {
        return referenceCache.get(config);
    }

    public static void destroy(ReferenceConfig config) {
        referenceCache.destroy(config);
    }

    public static void destroyAll() {
        referenceCache.destroyAll();
    }

    private static ReferenceConfig<GenericService> referenceConfig(DuService service) {
        ReferenceConfig<GenericService> config = new ReferenceConfig<>();
        config.setApplication(dubboApp);
        config.setInterface(service.getInterfaceClass());
        config.setGeneric(true);
        config.setUrl(dubboDirectUrl(service.getConnect()));
        config.setVersion(service.getVersion());
        return config;
    }

    private static String dubboDirectUrl(DuConnect connect) {
        return String.format(dubboCallTemplate, connect.ip(), connect.port());
    }
}
