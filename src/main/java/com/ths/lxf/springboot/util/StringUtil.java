package com.ths.lxf.springboot.util;

public class StringUtil {
    public static boolean isNull(Object object) {
        if (null == object || object.toString().trim().equals("")) {
            return true;
        }
        return false;
    }
}
