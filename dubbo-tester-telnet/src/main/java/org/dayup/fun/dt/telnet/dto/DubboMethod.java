package org.dayup.fun.dt.telnet.dto;

import java.util.ArrayList;
import java.util.List;

public class DubboMethod {

    private String apiClass;
    private String methodName;
    private String outParam;
    private List<String> inParams = new ArrayList<>();

    public String getApiClass() {
        return apiClass;
    }

    public void setApiClass(String apiClass) {
        this.apiClass = apiClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOutParam() {
        return outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }

    public List<String> getInParams() {
        return inParams;
    }

    public void setInParams(List<String> inParams) {
        this.inParams = inParams;
    }
}
