package com.hiido.shop.scawelshop.constant;


/**
 * @description: TODO http响应状态码常量类
 * @author YSLin
 * @date 2023/1/11 17:21
 */
public enum StatusCodeEnum {
    // 200 正常响应, 404 资源未发现
    SUCCESS(200, "请求成功"), NOT_FOUND(404, "资源未找到");
    private int code;
    private String message;
    StatusCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}