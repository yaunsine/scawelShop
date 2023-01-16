package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.config.MyHttpClientDownloader;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.service.IValidateImage;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * TODO: 图片链接请求处理，页面爬虫类
 */
@Service
public class ValidateImagePageProcess implements PageProcessor, IValidateImage {

    private int id;

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    @Resource
    ProductMapper productMapper;

    private final Logger logger = LoggerFactory.getLogger(ValidateImagePageProcess.class);


    // 设置请求UA，超时时间，重试次数，编码格式
    private Site site = Site.me().setSleepTime(1000).setRetryTimes(5).setTimeOut(10000).setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

    /**
     * 请求成功，状态码200会调用process方法，进行数据更新
     * @param page page
     */
    @Override
    public void process(Page page) {
        int statusCode = page.getStatusCode();
        logger.info("========================");
        logger.info("状态码：" + statusCode);

        int idd = Integer.parseInt(page.getUrl().toString().split("#")[1]);
        page.putField("id", idd);

        // 持久化到数据库
        productMapper.updateImgValidState(idd);
        logger.info("========================");
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Resource(name = "myConsoleP")
    private ConsolePipeline myConsolePipline;

    @Resource(name = "proxy")
    private Proxy proxy;

    @Resource(name = "httpClientDownloader")
    private MyHttpClientDownloader httpClientDownloader;


    /**
     * 爬虫启动入口
     * @param id 产品id
     * @param url 图片链接
     * @throws UnknownHostException 主机未知异常
     * @throws SocketTimeoutException socket超时异常
     */
    public void start(int id, String url) throws UnknownHostException, SocketTimeoutException {
        this.id = id;
        // 设置爬虫代理
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                proxy
        ));

        logger.info("请求链接#id： "+url+"#"+id);

        Spider spider = Spider.create(this)
                .addUrl(url+"#"+id)
                .addPipeline(myConsolePipline)
                .thread(1);
        spider.setDownloader(httpClientDownloader);
        // spider.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));
        spider.run();
    }

    public static void main(String[] args) {
        Spider.create(new ValidateImagePageProcess())
                .addUrl("https://cdn.shopify.com/s/files/1/0556/5258/1514/products/firming-breast-lotion-715130.jpg?v=1638355441")
                .addPipeline(new ConsolePipeline())
                .thread(1).run();
    }
}
