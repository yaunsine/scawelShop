package com.hiido.shop.scawelshop.controller;


import com.hiido.shop.scawelshop.model.ResultCode;
import com.hiido.shop.scawelshop.service.impl.FiverrSiteProcessor;
import com.hiido.shop.scawelshop.service.impl.FreelanceSiteProcessor;
import com.hiido.shop.scawelshop.util.ResultCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/fiverr")
@Api("Fiverr网站爬虫")
public class FiverrSpiderController {

    @Resource
    private FreelanceSiteProcessor freelanceSiteProcessor;

    @Resource
    private FiverrSiteProcessor fiverrSiteProcessor;

    private final Logger logger = LoggerFactory.getLogger(FiverrSpiderController.class);

    @GetMapping("/spiderGigReviews")
    @ResponseBody
    @ApiOperation("爬取评论信息")
    public ResultCode spiderGigReviews() {
        try {
            freelanceSiteProcessor.batchRequests();
            return ResultCodeUtil.success(200, "爬取完成");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResultCodeUtil.failure(400, "访问出错");
    }

    @GetMapping("/spiderGigs")
    @ResponseBody
    @ApiOperation("爬取Gig列表")
    public ResultCode spiderGigs() {
        fiverrSiteProcessor.seleniumStartBatch();
        return ResultCodeUtil.success(200, "爬取成功");
    }
}
