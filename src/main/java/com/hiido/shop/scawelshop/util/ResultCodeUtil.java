package com.hiido.shop.scawelshop.util;


import com.hiido.shop.scawelshop.model.ResultCode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description: TODO 响应体
 * @author YSLin
 * @date 2023/2/10 15:09
 */
public class ResultCodeUtil<T> {


    public static ResultCode<Object> success(int code, String msg, Object data) {
        return new ResultCode<>(code, msg, data);
    }

    public static ResultCode success(int code, String msg) {
        return new ResultCode<>(code, msg);
    }

    public static ResultCode<Object> success(int code) {
        return new ResultCode<>(code);
    }

    public static ResultCode<Object> failure(int code, String msg) {
        return new ResultCode<>(code, msg);
    }
}
