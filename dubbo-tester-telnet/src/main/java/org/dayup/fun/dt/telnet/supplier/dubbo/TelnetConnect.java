package org.dayup.fun.dt.telnet.supplier.dubbo;

import org.apache.commons.net.telnet.TelnetClient;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelenetCmdException;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.supplier.dubbo.exceptions.TelnetTimeoutException;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketTimeoutException;

public class TelnetConnect {

    private static String DEFAULT_AIX_PROMPT = "dubbo>";
    private TelnetClient client;
    private String ip;
    private int port;
    private InputStream in;
    private PrintStream out;
    private int socketTimeout = 300000;
    private int connectTimeout = 3000;
    private String encoding = "gbk";


    public TelnetConnect(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public TelnetConnect open() throws IOException {
        client = new TelnetClient();
        client.setConnectTimeout(connectTimeout);
        client.connect(ip, port);
        client.setSoTimeout(socketTimeout);
        in = client.getInputStream();
        out = new PrintStream(client.getOutputStream(), false, encoding);
        return this;
    }

    public void close() throws IOException {
        if (client != null) {
            client.disconnect();
        }
    }

    public String sendCmd(String command) throws TelnetException {
        write(command);
        return read();
    }

    private void write(String value) {
        out.println(value);
        out.flush();
    }

    private String read() throws TelnetException {
        StringBuilder builder = new StringBuilder();
        try {
            while (true) {
                char last = (char) in.read();
                builder.append(last);
                if (last == '>') {
                    String result = builder.toString().trim();
                    if (result.endsWith(DEFAULT_AIX_PROMPT)) {
                        result = result.substring(0, result.length() - DEFAULT_AIX_PROMPT.length());
                        return new String(result.getBytes("iso-8859-1"), encoding);
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            throw new TelnetTimeoutException("Read time out", e);
        } catch (IOException e) {
            throw new TelenetCmdException("Read telnet echo failure", e);
        }
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
