package com.hiido.shop.scawelshop.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.model.ResultCode;
import com.hiido.shop.scawelshop.util.PageMakerUtils;
import com.hiido.shop.scawelshop.util.ResultCodeUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Api("分页Controller")
public class ProductPageController {

    @Resource
    private ProductMapper productMapper;

    @GetMapping("/prod")
    @ResponseBody
    @ApiOperation("分页查询产品")
    public PageInfo<ProductModel> listProductsPagination(int pageSize, int pageNo) {
        PageHelper.startPage(pageNo, pageSize);

        List<ProductModel> products = productMapper.getProduct(1);
        PageInfo<ProductModel> pageInfos = new PageInfo<>(products);

        return pageInfos;
    }


    @GetMapping("/prodPage")
    @ResponseBody
    @ApiOperation("产品分页01")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功", response = ResultCode.class),
            @ApiResponse(code = 200, message = "请求成功", response = PageMakerUtils.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页的数量", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "当前页码", required = true, dataType = "int")
    })
    public ResultCode listProdPagination(int pageSize, int pageNo) {
        int productCount = productMapper.getProductCount();
        PageMakerUtils pageMakerUtils = new PageMakerUtils();
        pageMakerUtils.setPageSize(pageSize);
        pageMakerUtils.setPageNo(pageNo);
        pageMakerUtils.setTotal(productCount);
        List<ProductModel> productModelList = productMapper.listProductPagination(pageMakerUtils.getLimit(), pageMakerUtils.getOffset());
        // Map<String, Object> map = new HashMap<>();
        // map.put("data", productModelList);
        // map.put("page", pageMakerUtils);
        pageMakerUtils.setData(productModelList);

        return ResultCodeUtil.success(200, "请求成功", pageMakerUtils);
    }
}
