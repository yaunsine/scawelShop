package com.hiido.shop.scawelshop.mapper;


//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hiido.shop.scawelshop.bo.AdvertiserBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 广告商Mapper
 * @author YSLin
 */
@Mapper
public interface AdvertiserMapper {
    /**
     * 批量存储所有的广告商信息
     * @param advertiserDOList 广告商list
     * @return 插入操作是否成功
     */
    boolean saveBatchByNative(List<AdvertiserBO> advertiserDOList);

    /**
     * 查询所有的广告商
     * @return 广告商信息列表
     */
    List<AdvertiserBO> listAdvertisers();

    /**
     * 查询所有广告商Id
     * @return List<id>
     */
    List<Integer> listAdvertiserIds();
}
