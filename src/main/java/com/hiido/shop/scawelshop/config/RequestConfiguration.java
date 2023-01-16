package com.hiido.shop.scawelshop.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RequestConfiguration {
    private static int connectTimeout = 60000;
    private static int defaultMaxPerRoute = 500;
    private static int maxTotal = 2000;
    private static int socketTimeout = 60000;
    private static boolean staleConnectionCheckEnable = true;
    private static int connectRequestTimeout = 60000;

    private static String host = "121.13.252.58";

    private static int port = 41564;

    @Bean("requestConfig")
    public static RequestConfig build() {
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setConnectTimeout(connectTimeout);
        requestConfigBuilder.setSocketTimeout(socketTimeout);
//        requestConfigBuilder.setStaleConnectionCheckEnabled(staleConnectionCheckEnable);
        requestConfigBuilder.setConnectionRequestTimeout(connectRequestTimeout);
//        requestConfigBuilder.setProxy(new HttpHost(host, port));
        return requestConfigBuilder.build();
    }
}
