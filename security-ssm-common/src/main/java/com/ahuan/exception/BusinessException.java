package com.ahuan.exception;


public class BusinessException extends RuntimeException {

    //自定义错误码
    private String code;

    public BusinessException(){

    }

    //自定义构造器
    public  BusinessException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
