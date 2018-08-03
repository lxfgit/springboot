package com.ths.lxf.springboot.common;

import java.util.Map;

public class RestModel {
    private int msgCode = 1;
    private String msg = "ok";

    private Object data = null;

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
