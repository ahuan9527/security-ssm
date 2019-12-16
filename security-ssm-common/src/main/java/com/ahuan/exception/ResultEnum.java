package com.ahuan.exception;

public enum ResultEnum {

    UNKNOW_ERROR("999","未知错误！"),
    SUCCESS("001","成功"),
    FAILE_ERROR("002","未知错误"),
    NOTNULL_ERROR("003","参数为空!");

    private String code;
    private String msg;

    ResultEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
