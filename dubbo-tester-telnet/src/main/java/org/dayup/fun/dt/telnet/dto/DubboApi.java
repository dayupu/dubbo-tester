package org.dayup.fun.dt.telnet.dto;

public class DubboApi {

    private int number;
    private String apiClass;

    public DubboApi(int number, String apiClass) {
        this.number = number;
        this.apiClass = apiClass;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getApiClass() {
        return apiClass;
    }

    public void setApiClass(String apiClass) {
        this.apiClass = apiClass;
    }
}
