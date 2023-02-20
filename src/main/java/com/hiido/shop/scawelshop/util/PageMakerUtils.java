package com.hiido.shop.scawelshop.util;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import java.util.List;

@ApiModel("页码工具类")
public class PageMakerUtils {
    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private int pageNo = 1;
    /**
     * 每页的数据总量
     */
    @ApiModelProperty("每页的数据总量")
    private int pageSize;
    /**
     * 总数据条数
     */
    @ApiModelProperty("总数据条数")
    private int total;
    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int totalPage;
    /**
     * 每页限制
     */
    @ApiModelProperty("每页的限制")
    private int limit;
    /**
     * 偏置
     */
    @ApiModelProperty("偏置")
    private int offset;
    /**
     * 当前页的数量
     */
    @ApiModelProperty("当前页的数量")
    private int count;
    /**
     * 数据
     */
    @ApiModelProperty("数据列表")
    private List<?> data;



    public void setPageNo(int pageNo) {
        if (pageNo > 0) {
            this.pageNo = pageNo;
        } else {
            this.pageNo = 1;
        }
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 0 ? 5 : pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getTotalPage() {
        this.totalPage = total / pageSize + 1;
        return this.totalPage;
    }
    public int getLimit() {
        this.limit = pageSize;
        return this.limit;
    }
    public int getOffset() {
        this.offset = (pageNo - 1) * pageSize;
        return this.offset;
    }
    public int getCount() {
        if (pageNo < totalPage) {
            this.count = limit;
        } else {
            this.count = total - (pageNo - 1) * pageSize;
        }
        return this.count;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public List<?> getData() {
        return data;
    }
}



