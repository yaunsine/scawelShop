package com.hiido.shop.scawelshop.controller;


import com.hiido.shop.scawelshop.constant.HttpStatus;
import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.model.ResultCode;
import com.hiido.shop.scawelshop.service.IProductService;
import com.hiido.shop.scawelshop.service.impl.AsyncValidateAllLink;
import com.hiido.shop.scawelshop.service.impl.FiverrSiteProcessor;
import com.hiido.shop.scawelshop.util.ResultCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: TODO 爬虫servlet化
 * @author YSLin
 * @date 2023/1/12 17:27
 */
@RestController
@Api("爬虫类说明")
public class SpiderController {

    @Resource
    private IProductService productService;

    @Resource
    private AsyncValidateAllLink asyncValidateAllLink;

    @Resource
    private FiverrSiteProcessor fiverrSiteProcessor;

    @GetMapping("/batchAllLink")
    @ApiOperation("product表产品链接、图片链接全检测，异步实现")
    @ResponseBody
    public ResultCode getProduct() {
        asyncValidateAllLink.batchAllLink();
        return ResultCodeUtil.success(200, "执行完毕");
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation("访问首页")
    public ResultCode getHomePage() {
        return ResultCodeUtil.success(HttpStatus.SC_OK, "请求成功！此页是首页。");
    }

    @PutMapping("/index/{id}")
    @ResponseBody
    @ApiOperation("访问首页目录")
    public ResultCode getSubPage(@PathVariable("id")int id) {
        return ResultCodeUtil.success(200, "当前是" + id + "页");
    }

    @GetMapping("/check")
    @ResponseBody
    @ApiOperation("使用HttpClient检测404链接")
    public ResultCode checkUrl() {
        productService.spiderHttpClient();
        return ResultCodeUtil.success(200, "执行完毕");
    }
}
