package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.config.MyHttpClientDownloader;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.service.IValidateImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * TODO: 产品链接请求处理，页面爬虫类
 * @author YSLin
 */
@Service
public class ValidateLinkPageProcess implements PageProcessor, IValidateImage {

    @Value("${my.proxy.host}")
    private String proxyHost;

    @Value("${my.proxy.port}")
    private int proxyPort;

    @Resource
    ProductMapper productMapper;

    @Resource(name = "myConsoleP")
    private ConsolePipeline myConsolePipline;

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    private final Logger logger = LoggerFactory.getLogger(ValidateLinkPageProcess.class);

    /**
     * 设置请求UA，超时时间，重试次数，编码格式
     */
    private final Site site = Site.me().setSleepTime(1000).setTimeOut(10000).setRetryTimes(5).setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");


    /**
     * 请求成功，状态码200会调用process方法，进行数据更新
     * @param page page
     */
    @Override
    public void process(Page page) {
        int statusCode = page.getStatusCode();
        System.out.println(statusCode);
        int idd = Integer.parseInt(page.getUrl().toString().split("#")[1]);
        page.putField("id", idd);

        // 持久化到数据库
        productMapper.updateLinkValid(idd);
        logger.info("##################################");
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Resource(name = "proxy")
    private Proxy proxy;

    @Resource(name = "httpClientDownloader")
    private MyHttpClientDownloader httpClientDownloader;

    /**
     * 爬虫启动入口
     * @param id 产品链接编号
     * @param url 产品链接
     * @throws UnknownHostException 域名访问异常
     * @throws SocketTimeoutException socket超时异常
     */
    public void start(int id, String url) throws UnknownHostException, SocketTimeoutException {

        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                proxy
        ));
        Spider spider = Spider.create(this).addPipeline(myConsolePipline);
        spider.setDownloader(httpClientDownloader);
        spider.thread(1);
        spider.addUrl(url+"#"+id);
        spider.run();
    }

    public static void main(String[] args) {
        Spider.create(new ValidateLinkPageProcess())
                .addUrl("https://cdn.shopify.com/s/files/1/0556/5258/1514/products/firming-breast-lotion-715130.jpg?v=1638355441")
                .addPipeline(new ConsolePipeline())
                .thread(10).run();
    }
}
