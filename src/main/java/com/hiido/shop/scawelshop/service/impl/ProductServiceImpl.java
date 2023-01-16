package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.service.IProductService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: TODO 产品业务
 * @author YSLin
 * @date 2023/1/11 17:57
 */
@Service
@Log
public class ProductServiceImpl implements IProductService {

    @Resource
    ProductMapper productMapper;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Resource
    private AsyncRunSpider asyncRunSpider;

    @Resource
    private HttpClientService httpClientService;

    @Value("${productId:250639247}")
    private int productId;

    @Override
    public boolean spiderHttpClient() {
        int id = productId;
        int max = 323089504 + 1000;
        int offset = 1000;
        for(int i = id; i <= max; i += offset) {
            int limit = 1000;
            logger.info("当前进度为"+((i - id + 1)*100.0/(max - id + 1))+"%");
            logger.info(String.format("****************** id-offset:%d,%d", i, limit));
            List<ProductModel> productList = productMapper.getProduct(i);
            for (ProductModel productModel: productList) {
                try {
                      // httpClientService.doGetImgSrc(productModel.getImageSrc(), productModel.getId());
                      asyncRunSpider.asynRunHttpClient(productModel);
                } catch (Exception e) {
                    logger.error("线程异常: "+e.getMessage());
                    // throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    /**
     * TODO: 为防止内存溢出，每次从数据库取1000条进行处理
     */
    @Override
    public void getList() {
        int id = 283032707;
        int max = 323089504 + 1000;
        int offset = 1000;
        for(int i = id; i <= max; i += offset) {
            int limit = 1000;
            logger.info("当前进度为"+((i - id + 1)*100/(max - id + 1))+"%");
            logger.info(String.format("****************** id-offset:%d,%d", i, limit));
            List<ProductModel> productList = productMapper.getProduct(i);
            for (ProductModel productModel: productList) {
                try {
                    doSomethings(productModel);

                } catch (InterruptedException e) {
                    logger.error("线程异常: "+e.getMessage());
                }
            }
        }
    }


    /**
     * 构造线程异步方法
     * @param productModel 单个产品数据
     * @throws InterruptedException 中断异常
     * @deprecated
     */
    public void doSomethings(ProductModel productModel) throws InterruptedException {
        asyncRunSpider.asynRun(productModel);
    }

}
