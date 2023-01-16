package com.hiido.shop.scawelshop;

import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.model.ResultCode;

import java.util.List;

public class TTests {
    public static void main(String[] args) {
        ResultCode<List<ProductModel>> resultCode = new ResultCode<>(200, "成功", null);
        System.out.println(resultCode.toString());

    }
}
