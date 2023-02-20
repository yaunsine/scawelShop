package com.hiido.shop.scawelshop.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.hiido.shop.scawelshop.constant.FileSuffixConstants;
import com.hiido.shop.scawelshop.model.ResultCode;
import com.hiido.shop.scawelshop.model.SearchResult;
import com.hiido.shop.scawelshop.service.processor.BingPageProcessor;
import com.hiido.shop.scawelshop.util.ResultCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@Api("bing搜索爬取")
public class BingSearchSpiderController {

    @Resource
    private BingPageProcessor bingPageProcessor;

    @GetMapping("/bing/search")
    @ApiOperation("下载搜索结果")
    public void getSearchResult(@RequestParam("filename") String fileName, @RequestParam("keyword") String keyword, HttpServletResponse response) throws IOException {
        List<SearchResult> searchResults = bingPageProcessor.exportExcel(keyword);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();

            String suffix = FileSuffixConstants.XLS_SUFFIX;
            fileName = fileName + suffix;
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=" + fileName);
            EasyExcel.write(outputStream, SearchResult.class).autoCloseStream(Boolean.FALSE).sheet("模板").doWrite(searchResults);
        } catch (IOException e) {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultCodeUtil.failure(400, "下载失败...")));
            throw new RuntimeException("下载异常");
        }
        // byte[] bytes = ResultCodeUtil.failure(200, "下载成功！").toString().getBytes(StandardCharsets.UTF_8);
        // outputStream.write(bytes);
        // outputStream.flush();
        // return ResultCodeUtil.failure(200, "下载成功！");
    }
}
