package org.dayup.fun.dt.telnet.view;

import org.dayup.fun.dt.telnet.bean.DuConnect;
import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.bean.DuService;
import org.dayup.fun.dt.telnet.service.DubboService;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetException;

import java.util.Collections;
import java.util.List;

public class HomePresenter {

    private HomeView view;

    private DubboService dubboService = DubboService.getInstance();

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public List<DuMethod> dubboMethods(DuService service) {
        return dubboService.findMethods(service);
    }

    public void dubboServices(String ip, int port) throws TelnetException {
        DuConnect connect = new DuConnect(ip, port);
        List<DuService> duServices = dubboService.findServices(connect);
        view.setServerInfo(connect.info());
        view.refreshServices(Collections.unmodifiableList(duServices));
    }

}
