package org.dayup.fish.bean;


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
