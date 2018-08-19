package org.dayup.fun.dt.telnet.view.listener;

import org.dayup.fun.dt.telnet.client.exceptions.TelnetException;

import java.util.EventListener;

public interface TelnetListener extends EventListener {

    void telnetEvent(String ip, int port) throws TelnetException;

}
