package com.hiido.shop.scawelshop.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * TODO: 商品信息实体类
 */
@Data
public class ProductModel {
    /**
     * 产品编号
     */
    private int id;
    /**
     * 图片链接
     */
    private String imageSrc;
    /**
     * 产品链接
     */
    private String link;
    /**
     * 记录图片链接状态，1表示404，0表示可用
     */
    private int isDeleted;
    /**
     * 记录产品链接状态，1表示404，0表示可用
     */
    private int isLinkValid;

    private int isImgSrcDel;

    private int isProductLinkDel;

}
