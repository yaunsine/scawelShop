package com.hiido.shop.scawelshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.proxy.Proxy;


/**
 * TODO: 代理类配置，防止反爬虫机制
 */
@Configuration
public class MyProxyConfiguration {
    @Value("${my.proxy.host}")
    private String proxy_host;

    @Value("${my.proxy.port}")
    private int proxy_port;

    @Bean(name = "proxy")
    public Proxy getProxy() {
        return new Proxy(proxy_host, proxy_port);
    }
}
