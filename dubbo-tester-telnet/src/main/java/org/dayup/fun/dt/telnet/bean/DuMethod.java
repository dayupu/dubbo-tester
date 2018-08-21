package org.dayup.fun.dt.telnet.bean;

import org.dayup.fun.dt.telnet.supplier.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/20
 */
public class DuMethod implements Cloneable {

    private String serviceKey;

    private String name;

    private String returnType;

    private List<String> paramTypes = new ArrayList<>();


    public DuMethod(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public String simpleInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append(" (");
        String paramStr = StringUtils.join(paramTypes, ", ");
        builder.append(paramStr == null ? " " : paramStr);
        builder.append(")");
        return builder.toString();
    }

    @Override
    public DuMethod clone() {
        try {
            return (DuMethod) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
