package com.viapro.elec.util;

import org.apache.commons.lang3.StringUtils;

public class StringTool {

    public static String[] getArray(String strs, String reg) {
        if (StringUtils.isEmpty(strs)) {
            return null;
        }
        return strs.split(reg);
    }

}
