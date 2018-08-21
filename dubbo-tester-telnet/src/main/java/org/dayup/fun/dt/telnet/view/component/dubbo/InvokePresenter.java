package org.dayup.fun.dt.telnet.view.component.dubbo;

import org.dayup.fun.dt.telnet.bean.DuMethod;
import org.dayup.fun.dt.telnet.service.DubboService;
import org.dayup.fun.dt.telnet.supplier.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class InvokePresenter {

    private InvokeView view;

    DubboService dubboService = DubboService.getInstance();

    public InvokePresenter(InvokeView view) {
        this.view = view;
    }

    public String invoke(DuMethod method, String text) {
        return dubboService.invoke(method, text);
    }

    public String methodParams(DuMethod method) {
        if (method.getParamTypes().isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String placeholder;
        List<String> params = new ArrayList<>();
        for (String param : method.getParamTypes()) {
            if (param.equals(String.class.getName())) {
                placeholder = "\"\"";
            } else if (param.equals(Integer.class.getName()) || param.equals(Long.class.getName())
                    || param.equals(Float.class.getName()) || param.equals(Double.class.getName())) {
                placeholder = "0";
            } else if (param.equals(List.class.getName())) {
                placeholder = "[]";
            } else {
                placeholder = "null";
            }
            params.add(placeholder);
        }
        builder.append(StringUtils.join(params, ", "));
        builder.append("]");
        return builder.toString();
    }

}
