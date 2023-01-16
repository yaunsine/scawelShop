package com.hiido.shop.scawelshop.controller;


import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.service.IProductService;
import com.hiido.shop.scawelshop.service.impl.AsyncValidateAllLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: TODO 爬虫servlet化
 * @author YSLin
 * @date 2023/1/12 17:27
 */
@RestController
public class SpiderController {

    @Resource
    private IProductService productService;

    @Resource
    private AsyncValidateAllLink asyncValidateAllLink;

    @GetMapping("/")
    @ResponseBody
    public String getProduct() {
        asyncValidateAllLink.batchAllLink();
        return "{'code':200, 'msg': '执行完毕'}";
    }

    @GetMapping("/check")
    @ResponseBody
    public String checkUrl() {
        productService.spiderHttpClient();
        return "{'code':200, 'msg': '执行完毕'}";
    }
}
