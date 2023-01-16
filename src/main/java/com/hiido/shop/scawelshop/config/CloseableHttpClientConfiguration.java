package com.hiido.shop.scawelshop.config;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class CloseableHttpClientConfiguration {
    @Bean("httpClient")
    public CloseableHttpClient httpClientBuilder() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(cm)
                .setRetryHandler(new StandardHttpRequestRetryHandler()).build();
        return httpClient;
    }
}
