package com.hiido.shop.scawelshop.mapper;


import com.hiido.shop.scawelshop.model.ProductModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * TODO: product_list_new_v4表的dao层处理
 */
@Mapper
@Repository
public interface ProductMapper {

    /**
     * TODO: 设置is_link_valid为0，表示产品链接为非404
     * @param id: 产品编号
     * @return  修改结果返回为影响行数
     */
    @Update("update product_list_new_v4 set is_link_valid = 0 where id = #{id}")
    public int updateLinkValid(int id);

    @Update("update product_list_new_v4 set is_image_src_del = 0 where id = #{id}")
    public int updateImageSrcValid(int id);

    @Update("update product_list_new_v4 set is_image_src_del = 1 where id = #{id}")
    public int updateImageSrcDel(int id);

    @Update("update product_list_new_v4 set is_product_link_del = 1 where id = #{id}")
    public int updateProductLinkDel(int id);

    /**
     * TODO: 获取产品链接，用于判断是否存在404的情况
     * @param offset
     * @param limit
     * @return
     */
    @Deprecated
    @Select("SELECT id, CONCAT('https://', domain, '/products/', handle) as link, is_link_valid as is_deleted from product_list_new_v4 where is_link_valid = 1 limit #{offset}, #{limit}")
    public List<ProductModel> getLinks(@Param("offset") int offset, @Param("limit")int limit);

    /**
     * TODO: 获取图片链接和产品链接，用于判断两个链接的状态码
     * @param offset
     * @param limit
     * @return
     */
    @Select("select id, image_src,CONCAT('https://', domain, '/products/', handle) as link, is_deleted as isDeleted, is_link_valid as isLinkValid, is_image_src_del as isImageSrcDel, is_product_link_del as isProductLinkDel from product_list_new_v4 where id >= #{id} limit 1000")
    public List<ProductModel> getProduct(@Param("id") int id);

    /**
     * TODO: 修改图片链接为可用状态，非404为1，404为0
     * @param id
     * @return
     */
    @Update("update product_list_new_v4 set is_deleted = 0 where id = #{id}")
    public int updateImgValidState(int id);
}
