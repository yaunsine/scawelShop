package com.hiido.shop.scawelshop.service;

import com.hiido.shop.scawelshop.model.ProductModel;

import java.util.List;


/**
 * 产品链接处理业务接口
 */
public interface IProductService {
    void getList();

    boolean spiderHttpClient();
}
