package com.hiido.shop.scawelshop.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author YSLin
 */

@ApiModel("ResultCode状态类")
@Data
public class ResultCode<T> {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", example = "200")
    private int code;
    /**
     * 响应信息
     */
    @ApiModelProperty("状态信息")
    private String msg;
    /**
     * 响应数据
     */
    @ApiModelProperty("状态数据")
    private T data;

    public ResultCode(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultCode(int code) {
        this.code = code;
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
