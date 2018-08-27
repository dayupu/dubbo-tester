package org.dayup.fish.bean;

import java.util.List;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/20
 */
public class DuService implements Cloneable {

    private DuConnect connect;
    private String application;
    private String interfaceClass;
    private String dubboVersion;
    private String defaultTimeout;
    private Boolean generic = false;
    private String methodStr;
    private String pid;
    private String revision;
    private String side;
    private String version;
    private List<DuMethod> methods;

    public DuService(DuConnect connect) {
        this.connect = connect;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getDubboVersion() {
        return dubboVersion;
    }

    public void setDubboVersion(String dubboVersion) {
        this.dubboVersion = dubboVersion;
    }

    public String getDefaultTimeout() {
        return defaultTimeout;
    }

    public void setDefaultTimeout(String defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public Boolean getGeneric() {
        return generic;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public String getMethodStr() {
        return methodStr;
    }

    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }

    public List<DuMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<DuMethod> methods) {
        this.methods = methods;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public DuConnect getConnect() {
        return connect;
    }

    public String key() {
        return interfaceClass + "-" + (version == null ? "" : version);
    }

    @Override
    public DuService clone() {
        try {
            return (DuService) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
