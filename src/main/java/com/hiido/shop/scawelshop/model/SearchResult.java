package com.hiido.shop.scawelshop.model;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SearchResult {
    @ApiModelProperty("链接")
    @ExcelProperty("链接")
    private String cite;
    @ExcelProperty("标题")
    @ApiModelProperty("标题")
    private String title;
    @ExcelProperty("符合TPos的商店名称")
    @ApiModelProperty("tPos解析名称")
    private String tPosName;
    @ExcelIgnore
    @ApiModelProperty("描述")
    private String description;
}
