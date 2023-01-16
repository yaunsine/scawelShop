package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @description: TODO 异步验证多链接
 * @author YSLin
 * @date 2023/1/13 9:56
 */
@Service
public class AsyncValidateAllLink {
    @Resource
    private ProductMapper productMapper;

    @Resource
    private ValidateAllLinkPageProcess validateAllLinkPageProcess;

    @Value("${idd:271724461}")
    private int id;

    private final Logger logger = LoggerFactory.getLogger(AsyncValidateAllLink.class);

    public void batchAllLink() {
        int max = 323089504 + 1000;
        int limit = 1000;
        for (int offset = id; offset < max; offset += limit) {
            double progressValue = (offset - id) * 100.0 / (max - id);
            logger.info(String.format("进度: %.2f%%", progressValue));
            List<ProductModel> productModelList = productMapper.getProduct(offset);
            if (productModelList.size() != 0) {
                try {
                    validateAllLinkPageProcess.start(productModelList);
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                } catch (SocketTimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
