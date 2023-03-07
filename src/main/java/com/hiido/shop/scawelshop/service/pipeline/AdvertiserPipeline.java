package com.hiido.shop.scawelshop.service.pipeline;

import com.hiido.shop.scawelshop.bo.AdvertiserBO;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @author YSLin
 * TODO:
 */
public class AdvertiserPipeline implements PageModelPipeline<AdvertiserBO> {

    @Override
    public void process(AdvertiserBO advertiserBO, Task task) {
        if (advertiserBO == null) {}
    }
}
