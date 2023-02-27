package com.hiido.shop.scawelshop.service.processor;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.hiido.shop.scawelshop.constant.FileSuffixConstants;
import com.hiido.shop.scawelshop.model.ResultCode;
import com.hiido.shop.scawelshop.model.SearchResult;
import com.sun.javafx.css.Selector;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.CssSelector;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class BingPageProcessor implements PageProcessor {

    static List<SearchResult> list = new ArrayList<>();

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> bResultList = html.xpath("//li[@class='b_algo']").all();

        for (String item : bResultList) {
            Html itemHtml = Html.create(item);
            String cite = itemHtml.xpath("cite").get().replaceAll("<.*?>", "");
            String title = itemHtml.xpath("div[@class='b_title']/h2/a").get().replaceAll("<.*?>", "");
            String tPosName = "";
            if (cite.contains(".tpos.vn")) {
                tPosName = cite.substring(cite.lastIndexOf("/") + 1, cite.indexOf(".tpos.vn"));
            }
            SearchResult searchResult = new SearchResult();
            searchResult.setCite(cite);
            searchResult.setTitle(title);
            searchResult.setTPosName(tPosName);
            list.add(searchResult);
        }
        list = Collections.synchronizedList(new ArrayList<>(new HashSet<>(list)));

        page.addTargetRequests(listUrl());
    }

    @Override
    public Site getSite() {
        return PageProcessor.super.getSite();
    }

    public static void write(String fileName) {
        String fullFileName = fileName + FileSuffixConstants.XLS_SUFFIX;
        EasyExcel.write(fullFileName, SearchResult.class)
                .excelType(ExcelTypeEnum.XLS)
                .sheet("sheet100")
                .doWrite(list);
    }

    public List<String> listUrl() {
        List<String> urlList = new ArrayList<>();
        for (int i = 10; i < 60; i += 10) {
            String url = "https://cn.bing.com/search?q=%22.tpos.vn%22&first="+i;
            urlList.add(url);
        }
        return urlList;
    }

    public List<SearchResult> exportExcel(String keyword) {
        Spider spider = Spider.create(new BingPageProcessor());
        String url = "https://cn.bing.com/search?q=%22"+keyword+"%22";
        spider.addUrl(url).thread(6);
        spider.run();
        // write(fileName);
        return list;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new BingPageProcessor());
        String url = "https://cn.bing.com/search?q=%22tpos.vn%22&qs=n&sp=-1&pq=%22tpos.vn%22&sc=1-9&sk=&cvid=1245F11AFB894D17AE3F797C2654ABE0&ghsh=0&ghacc=0&ghpl=&first=1&FORM=PERE";
        spider.addUrl(url);
        spider.run();
        write("temp");
    }
}
