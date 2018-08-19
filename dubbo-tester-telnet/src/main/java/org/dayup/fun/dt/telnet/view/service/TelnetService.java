package org.dayup.fun.dt.telnet.view.service;

import org.dayup.fun.dt.telnet.client.TelnetConnect;
import org.dayup.fun.dt.telnet.dto.DubboApi;
import org.dayup.fun.dt.telnet.dto.DubboMethod;
import org.dayup.fun.dt.telnet.client.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.client.TelnetPool;
import org.dayup.fun.dt.telnet.view.IndexView;
import org.dayup.fun.dt.telnet.view.listener.TelnetListener;

import java.util.ArrayList;
import java.util.List;

public class TelnetService implements TelnetListener {

    private IndexView view;
    private String ip;
    private int port;
    private final static String LINE = System.lineSeparator();

    public TelnetService(IndexView view) {
        this.view = view;
    }

    public String invoke(DubboMethod method, String param) throws TelnetException {
        StringBuilder builder = new StringBuilder();
        builder.append("invoke ");
        builder.append(method.getApiClass());
        builder.append(".");
        builder.append(method.getMethodName());
        builder.append("(");
        if (param != null) {
            builder.append(param);
        }
        builder.append(")");

        StringBuilder result = new StringBuilder();
        result.append("【dubbo cmd】");
        result.append(System.lineSeparator());
        result.append(builder.toString());
        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        result.append("【result】");
        result.append(System.lineSeparator());
        result.append(sendCmd(builder.toString()));
        return result.toString();
    }


    public List<DubboMethod> queryApiMethods(DubboApi api) {
        List<DubboMethod> data = new ArrayList<>();
        String response = null;
        try {
            response = sendCmd("ls -l " + api.getApiClass());
        } catch (TelnetException e) {
            e.printStackTrace();
        }
        if (response != null) {
            DubboMethod method;
            for (String line : response.split(LINE)) {
                method = new DubboMethod();
                line = line.trim();
                if (!line.endsWith(")")) {
                    continue;
                }
                method.setApiClass(api.getApiClass());
                line = line.substring(0, line.length() - 1);
                String[] body = line.split("\\s+", 2);
                method.setOutParam(body[0]);
                String[] footer = body[1].split("\\(", 2);
                method.setMethodName(footer[0]);
                String[] params = footer[1].split("\\s+");
                for (String param : params) {
                    method.getInParams().add(param);
                }
                data.add(method);
            }
        }
        return data;
    }

    @Override
    public void telnetEvent(String ip, int port) throws TelnetException {
        this.ip = ip;
        this.port = port;
        String response = sendCmd("ls");
        view.setConnServerInfo(ip + ":" + port);
        if (response != null) {
            List<DubboApi> apiList = new ArrayList<>();
            int index = 0;
            for (String className : response.split(LINE)) {
                apiList.add(new DubboApi(++index, className));
            }
            view.refreshApis(apiList);
        }
    }

    private String sendCmd(String cmd) throws TelnetException {
        return TelnetPool.openConnect(ip, port).sendCmd(cmd);
    }

}
