package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.constant.StatusCodeEnum;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * @description: TODO 处理httpClient业务
 * @author YSLin
 * @date 2023/1/11 17:54
 */
@Service
public class HttpClientService {

    @Resource(name = "httpClient")
    private CloseableHttpClient httpClient;

    private final Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    @Resource
    private ProductMapper productMapper;

    @Resource(name = "requestConfig")
    private RequestConfig requestConfig;

    public void doGetImgSrc(String url, int id) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpGet.setConfig(requestConfig);
        logger.info("请求链接: " + url + ", id: " + id);
        CloseableHttpResponse response = null;
        try {
            Thread.sleep(2000);
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == StatusCodeEnum.SUCCESS.getCode()) {

                logger.info("=================状态码:200===================");
                logger.info("productId: " + id);

            }
            if(response.getStatusLine().getStatusCode() == StatusCodeEnum.NOT_FOUND.getCode()) {
                logger.info("404响应..." + url);
                productMapper.updateImageSrcDel(id);
                logger.info("productId: " + id);
                // 持久化到数据库
                logger.info("********更新[图片]404状态到数据库**************");
            }
        } catch (IOException e) {
            logger.warn("请求异常: " + e.getMessage());
            logger.error("请求链接超时: " + url);
//            throw new RuntimeException("代理服务器出错");
        } catch (InterruptedException e) {
            logger.warn("线程异常: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if(response != null) {
                response.close();
            }
        }
    }


    public void doGetProductLink(String productUrl, int id) throws IOException {
        HttpGet httpGet = new HttpGet(productUrl);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpGet.setConfig(requestConfig);
        logger.info("请求链接: " + productUrl + ", id: " + id);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == StatusCodeEnum.SUCCESS.getCode()) {
                logger.info("#################状态码:200#####################");

            }
            if(response.getStatusLine().getStatusCode() == StatusCodeEnum.NOT_FOUND.getCode()) {
                logger.info("404响应..." + productUrl);
                logger.info("productId: " + id);
                // 持久化到数据库
                productMapper.updateProductLinkDel(id);
                logger.info("********更新[产品链接]404状态到数据库**************");
            }
        } catch (IOException e) {
            logger.warn("请求异常: " + e.getMessage());
            logger.error("请求链接超时: " + productUrl);
//            throw new RuntimeException("代理服务器出错");
        }
        finally {
            if(response != null) {
                response.close();
            }
        }
    }
}
