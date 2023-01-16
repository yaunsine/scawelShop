package com.hiido.shop.scawelshop.model;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author YSLin
 */
public class ResultCode<T> {
    private int code;
    private String msg;
    private T data;
    public ResultCode(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder objectString = new StringBuilder();
        objectString.append("{\"code\":");
        objectString.append(this.code);
        objectString.append(", \"msg\": \"");
        objectString.append(this.msg);
        objectString.append("\", \"data\": ");
        objectString.append(this.data);
        objectString.append("}");
        return objectString.toString();
    }
}
