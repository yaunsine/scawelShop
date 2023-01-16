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

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    public Page download(Request request, Task task) {
        if (task != null && task.getSite() != null) {
            CloseableHttpResponse httpResponse = null;
            CloseableHttpClient httpClient = this.getHttpClient(task.getSite());
            Proxy proxy = this.proxyProvider != null ? this.proxyProvider.getProxy(task) : null;
            HttpClientRequestContext requestContext = this.httpUriRequestConverter.convert(request, task.getSite(), proxy);
            Page page = Page.fail();

            Page var9;
            try {
                httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
                page = this.handleResponse(request, request.getCharset() != null ? request.getCharset() : task.getSite().getCharset(), httpResponse, task);
                this.onSuccess(request, task);
                this.logger.info("downloading page success {}", request.getUrl());
                Page var8 = page;
                return var8;
            } catch (IOException var13) {
                this.onError(request, task, var13);
                this.logger.info("download page {} error", request.getUrl(), var13);
                var9 = page;
            } finally {
                if (httpResponse != null) {
                    EntityUtils.consumeQuietly(httpResponse.getEntity());
                }

                if (this.proxyProvider != null && proxy != null) {
                    this.proxyProvider.returnProxy(proxy, page, task);
                }
            }
            return var9;
        } else {
            throw new NullPointerException("task or site can not be null");
        }
    }


    @Override
    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        Page page = super.handleResponse(request, charset, httpResponse, task);
        int code = page.getStatusCode();
        logger.info("状态码响应为: {}", code);
        return page;
    }

    @Bean(name = "httpClientDownloader")
    public MyHttpClientDownloader getHttpClient() {
        return new MyHttpClientDownloader();
    }
}
