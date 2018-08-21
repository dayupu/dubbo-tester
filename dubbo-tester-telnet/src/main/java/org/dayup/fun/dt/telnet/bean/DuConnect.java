package org.dayup.fun.dt.telnet.bean;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/20
 */
public class DuConnect {

    private String ip;
    private Integer port;

    public DuConnect(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String ip() {
        return ip;
    }

    public Integer port() {
        return port;
    }

    public String info() {
        return ip + ":" + port;
    }
}
