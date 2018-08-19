package org.dayup.fun.dt.telnet.client;

import org.apache.commons.net.telnet.TelnetClient;
import org.dayup.fun.dt.telnet.client.exceptions.TelenetCmdException;
import org.dayup.fun.dt.telnet.client.exceptions.TelnetException;
import org.dayup.fun.dt.telnet.client.exceptions.TelnetTimeoutException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        out = new PrintStream(client.getOutputStream());
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
        char lastChar = DEFAULT_AIX_PROMPT.charAt(DEFAULT_AIX_PROMPT.length() - 1);

        try {
            byte[] buffer = new byte[2048];
            in.read(buffer);
            String result = new String(buffer, "utf-8");
            int emptyAt = result.indexOf('\u0000');
            if (emptyAt != -1) {
                result = result.substring(0, emptyAt);
            }
            System.out.println(result);
            if (result.endsWith(DEFAULT_AIX_PROMPT)) {
                result = result.substring(0, result.length() - DEFAULT_AIX_PROMPT.length());
            }
            return result;
//            char ch = (char) in.read();
//            while (true) {
//                builder.append(ch);
//                System.out.println(builder.toString());
//                if (ch == lastChar) {
//                    String receive = builder.toString();
//                    if (receive.endsWith(DEFAULT_AIX_PROMPT)) {
//                        receive = receive.substring(0, receive.length() - DEFAULT_AIX_PROMPT.length());
//                        return new String(receive.getBytes("iso8859-1"), "utf-8");
//                    }
//                }
//                ch = (char) in.read();
//            }
        } catch (SocketTimeoutException e) {
            throw new TelnetTimeoutException("Read time out", e);
        } catch (IOException e) {
            throw new TelenetCmdException("Read telnet echo failure", e);
        }
    }

    public boolean isAvailable() {
        try {
            if (client == null || in == null) {
                return false;
            }
            sendCmd("status");
            return true;
        } catch (TelnetException e) {
            return false;
        }
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
