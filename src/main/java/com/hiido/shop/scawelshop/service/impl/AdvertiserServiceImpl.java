package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.bo.AdvertiserBO;
import com.hiido.shop.scawelshop.mapper.AdvertiserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description: TODO 广告商业务类
 * @author YSLin
 * @date 2023/1/11 17:00
 */
@Service("advertiserService")
public class AdvertiserServiceImpl {
    @Resource
    private AdvertiserMapper advertiserMapper;

    /**
     * 批量处理业务，调用dao层批量插入
     * @param advertiserBOList 广告商列表
     * @return 批量插入是否成功
     */
    boolean saveBatchNative(List<AdvertiserBO> advertiserBOList) {
        boolean flag = false;
        flag = advertiserMapper.saveBatchByNative(advertiserBOList);
        return flag;
    }
}
