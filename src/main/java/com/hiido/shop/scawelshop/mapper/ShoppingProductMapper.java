package com.hiido.shop.scawelshop.mapper;

import com.hiido.shop.scawelshop.po.ShoppingProductPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLSyntaxErrorException;
import java.util.List;


/**
 * cj库中的产品Mapper
 * @author YSLin
 */
@Mapper
public interface ShoppingProductMapper{
    @Delete("delete from affiliate_cj_shop_product where advertiser_id = #{advertiserId}")
    boolean deleteShoppingProductsByAdvertiserId(@Param("advertiserId") int advertiserId);

    boolean saveBatchByNative(List<ShoppingProductPO> shoppingProductPOList) throws SQLSyntaxErrorException;
}
