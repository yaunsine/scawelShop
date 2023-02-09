package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.config.MyHttpClientDownloader;
import com.hiido.shop.scawelshop.config.ValidateHttpClientDownloader;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import com.hiido.shop.scawelshop.model.ProductModel;
import com.hiido.shop.scawelshop.service.IValidateImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;


/**
 * @description: TODO 验证所有链接200
 * @author YSLin
 * @date 2023/1/12 18:03
 */
@Service
public class ValidateAllLinkPageProcess implements PageProcessor, IValidateImage {

    private int id;

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    @Resource
    ProductMapper productMapper;

    private final Logger logger = LoggerFactory.getLogger(ValidateAllLinkPageProcess.class);


    // 1设置请求UA，超时时间，重试次数，编码格式
    private Site site = Site.me().setSleepTime(1000).setTimeOut(30*60*1000).setCharset("utf-8")
            .setCycleRetryTimes(5)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

    /**
     * 请求成功，状态码200会调用process方法，进行数据更新
     * @param page page
     */
    @Override
    public void process(Page page) {


    }

    @Override
    public Site getSite() {
        return site;
    }

    @Resource(name = "myConsoleP")
    private ConsolePipeline myConsolePipline;

    @Resource(name = "proxy")
    private Proxy proxy;

    @Resource(name = "validateHttpClientDownloader")
    private ValidateHttpClientDownloader httpClientDownloader;

    private Scheduler scheduler;

    @Autowired
    private void setSchedule() {
        this.scheduler = new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000));
    }
    /**
     * 爬虫启动入口
     * @throws UnknownHostException 主机未知异常
     * @throws SocketTimeoutException socket超时异常
     */
    @Async("threadValidateAllLink")
    public void start(List<ProductModel> productList) throws UnknownHostException, SocketTimeoutException {
        // 设置爬虫代理
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
//                proxy
//        ));

        int threads = 100;
        Spider spider = Spider.create(this);
        for (ProductModel productModel: productList) {
            id = productModel.getId();
            try {
                if (productModel.getIsDeleted() != 0) {
                    if(isUrlNotNull(productModel.getImageSrc())) {
                        spider.addUrl(productModel.getImageSrc() + "#img." + id);
                    } else {
                        logger.info("图片" + id + "为NuLL");
                    }
                }
                if (productModel.getIsLinkValid() != 0) {
                    if(isUrlNotNull(productModel.getLink())) {
                        spider.addUrl(productModel.getLink() + "#product." + id);
                    } else {
                        logger.info("产品" + id + "为NuLL");
                    }
                }
            } catch (Exception e) {
                logger.error("线程异常: "+e.getMessage());
                // throw new RuntimeException(e);
            }
        }
        spider.addPipeline(myConsolePipline).thread(threads);
        spider.setDownloader(httpClientDownloader);
        spider.setScheduler(scheduler);
        spider.run();
        spider.close();
        // spider = null;

        spider.close();
        spider = null;
        System.gc();
    }

    private boolean isUrlNotNull(String url) {
        return url != null && !url.isEmpty();
    }

    public static void main(String[] args) {
        ValidateHttpClientDownloader downloader = new ValidateHttpClientDownloader();
        Spider spider = Spider.create(new ValidateAllLinkPageProcess())
                .setDownloader(downloader)
                .addUrl("https://cdn.shopify.com/s/files/1/0556/5258/1514/products/firming-breast-lotion-715130.jpg?v=1638355441")
                .addPipeline(new ConsolePipeline())
                ;
        spider.thread(1).run();
        spider.close();
        spider = null;
        System.gc();
        System.out.println("执行end");
    }
}
