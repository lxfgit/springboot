package com.ths.lxf.springboot.common;

import java.util.HashMap;
import java.util.Map;

public class ErrorResultModel {
    private int errorCode = 1;
    private String errorMsg;
    private Map<String, String> data = new HashMap<>();

    public ErrorResultModel(Exception e) {
        errorMsg = "服务器异常";
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
