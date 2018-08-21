package org.dayup.fun.dt.telnet.supplier.config;


import org.dayup.fun.dt.telnet.supplier.config.prop.PropFile;
import org.dayup.fun.dt.telnet.supplier.config.prop.PropHandler;
import org.dayup.fun.dt.telnet.supplier.config.prop.PropKey;

@PropFile("/config.properties")
public class Configs {

    @PropKey("home.width")
    private Integer homeWidth;

    @PropKey("home.height")
    private Integer homeHeight;

    @PropKey("dubbo.invoke.width")
    private Integer invokeWidth;

    @PropKey("dubbo.invoke.height")
    private Integer invokeHeight;

    @PropKey("last.connect.server.ip")
    private String lastServerIp;

    @PropKey("last.connect.server.port")
    private String lastServerPort;

    public static Configs load() {
        return PropHandler.parse(Configs.class, false);
    }

    public void store() {
        PropHandler.store(this);
    }

    public Integer getHomeWidth() {
        return homeWidth;
    }

    public Integer getHomeHeight() {
        return homeHeight;
    }

    public Integer getInvokeWidth() {
        return invokeWidth;
    }

    public Integer getInvokeHeight() {
        return invokeHeight;
    }

    public String getLastServerIp() {
        return lastServerIp;
    }

    public String getLastServerPort() {
        return lastServerPort;
    }

    public void setLastServer(String lastServerIp, String lastServerPort) {
        this.lastServerIp = lastServerIp;
        this.lastServerPort = lastServerPort;
    }

    public void setHomeSize(Integer width, Integer height) {
        this.homeWidth = width;
        this.homeHeight = height;
    }

    public void setInvokeSize(Integer width, Integer height) {
        this.invokeWidth = width;
        this.invokeHeight = height;
    }

}
