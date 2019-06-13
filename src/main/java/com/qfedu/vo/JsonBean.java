package com.qfedu.vo;

public class JsonBean {

    private int code;
    private Object info;

    public JsonBean(){}

    public JsonBean(int code, Object info){
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
