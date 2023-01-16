package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * @description: TODO 封装异步请求，同时检测链接和图片
 * @author YSLin
 * @date 2023/1/11 16:59
 */
@Service
public class AsyncRunSpider {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    private ValidateImagePageProcess validateImagePageProcess;

    @Resource
    private ValidateLinkPageProcess validateLinkPageProcess;

    /**
     * TODO: 判断链接是否存在为Null值的情况
     * @param url 地址链接
     * @return 非空返回true，空返回false
     */
    private boolean isUrlNotNull(String url) {
        return url != null && !url.isEmpty();
    }

    @Resource
    private HttpClientService httpClientService;

    @Resource
    private ProductMapper productMapper;


    /**
     * 异步同时处理两个链接：产品链接、图片链接
     * @param productModel 传入包含链接和图片的产品
     */
    @Async("threadHttpClient")
    public void asynRunHttpClient(ProductModel productModel) {
        int id = productModel.getId();
        logger.info("product-id-" + id);
        try {
            if(isUrlNotNull(productModel.getImageSrc())) {
                httpClientService.doGetImgSrc(productModel.getImageSrc(), id);
            } else {
                logger.info("图片" + id + "为NuLL");
                productMapper.updateImageSrcDel(id);
            }
        } catch (IOException e) {
            logger.info("图片请求异常");
            throw new RuntimeException(e);
        }
        try {
            if(isUrlNotNull(productModel.getLink())) {
                httpClientService.doGetProductLink(productModel.getLink(), id);
            } else {
                logger.info("产品" + id + "为NuLL");
                productMapper.updateProductLinkDel(id);
            }
        } catch (IOException e) {
            logger.info("宝贝链接请求异常");
            throw new RuntimeException(e);
        }
    }

    /**
     * 异步请求方法，用于同时请求图片和链接，检测请求状态码
     * @param productModel 单个产品数据
     * @throws InterruptedException 异常
     */
    @Async("doSomethingImage")
    public void asynRun(ProductModel productModel) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("product-id-" + productModel.getId());
        try {
            if (isUrlNotNull(productModel.getImageSrc()) && productModel.getIsDeleted() != 0) {
                validateImagePageProcess.start(productModel.getId(), productModel.getImageSrc());
            }
        } catch (UnknownHostException e) {
            logger.error("UnknownHostException: "+e.getMessage());
        } catch (SocketTimeoutException socketEx) {
            logger.error("SocketTimeoutException: "+socketEx.getMessage());
        }
        try {
            if(isUrlNotNull(productModel.getLink()) && productModel.getIsLinkValid() != 0) {
                validateLinkPageProcess.start(productModel.getId(), productModel.getLink());
            }
        } catch (UnknownHostException e) {
            logger.error("UnknownHostException: "+e.getMessage());
        } catch (SocketTimeoutException socketEx) {
            logger.error("SocketTimeoutException: "+socketEx.getMessage());
        }
    }
}
