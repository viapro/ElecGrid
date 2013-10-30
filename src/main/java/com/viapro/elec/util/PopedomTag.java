package com.viapro.elec.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

public class PopedomTag extends SimpleTagSupport {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String name = getName();
        String popedom = (String) ServletActionContext.getContext().getSession().get("globel_popedom");
        if (!StringUtils.isBlank(name)) {
            if (name.contains(",")) {
                for (String n : StringTool.getArray(name, ",")) {
                    if (popedom.contains(n)) {
                        this.getJspBody().invoke(null);
                        return;
                    }
                }
            } else if (popedom.contains(name)) {
                this.getJspBody().invoke(null);
                return;
            }
        }
    }

}
