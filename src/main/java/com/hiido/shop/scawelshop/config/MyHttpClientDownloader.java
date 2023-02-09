package com.hiido.shop.scawelshop.config;

import com.hiido.shop.scawelshop.constant.HttpStatus;
import com.hiido.shop.scawelshop.mapper.ProductMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.downloader.HttpClientRequestContext;
import us.codecraft.webmagic.downloader.HttpUriRequestConverter;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 重写HttpClientDownloader类，捕获请求超时异常
 */
@Configuration
public class MyHttpClientDownloader extends HttpClientDownloader{

    private Logger logger = LoggerFactory.getLogger(MyHttpClientDownloader.class);


    private HttpClientGenerator httpClientGenerator;

    private HttpUriRequestConverter httpUriRequestConverter;

    private ProxyProvider proxyProvider;

    private Map<String, CloseableHttpClient> httpClients;

    @Resource
    private ProductMapper productMapper;

    @Autowired
    private void setHttpClientGenerator() {
        this.httpClientGenerator = new HttpClientGenerator();
    }

    @Autowired
    private void setHttpClients() {
        this.httpClients = new HashMap<String, CloseableHttpClient>();
    }


    @Autowired
    private void setHttpUriRequestConverter() {
        this.httpUriRequestConverter = new HttpUriRequestConverter();
    }

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }



    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = super.handleResponse(request, charset, httpResponse, task);
        int code = page.getStatusCode();
        logger.info("状态码响应为1: {}", code);
        return page;
    }

    @Bean(name = "httpClientDownloader")
    public MyHttpClientDownloader getHttpClient() {
        return new MyHttpClientDownloader();
    }
}
