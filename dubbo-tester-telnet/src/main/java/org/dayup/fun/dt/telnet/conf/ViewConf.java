package org.dayup.fun.dt.telnet.conf;


import org.dayup.fun.dt.telnet.support.PropHandler;
import org.dayup.fun.dt.telnet.support.annotation.PropFile;
import org.dayup.fun.dt.telnet.support.annotation.PropKey;

@PropFile("view.properties")
public class ViewConf {

    @PropKey("window.width")
    private Integer windowWidth;

    @PropKey("window.height")
    private Integer windowHeight;

    public static ViewConf initWithProp() {
        return PropHandler.parse(ViewConf.class, false);
    }

    public void store() {
        PropHandler.store(this);
    }

    public Integer getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(Integer windowWidth) {
        this.windowWidth = windowWidth;
    }

    public Integer getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(Integer windowHeight) {
        this.windowHeight = windowHeight;
    }
}
