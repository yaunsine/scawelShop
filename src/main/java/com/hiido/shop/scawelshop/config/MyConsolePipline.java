package com.hiido.shop.scawelshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.pipeline.ConsolePipeline;


/**
 * 管道配置类，设置成ConsolePipline
 */
@Configuration
public class MyConsolePipline {
    @Bean("myConsoleP")
    public ConsolePipeline getConsolePipeline() {
        return new ConsolePipeline();
    }
}
