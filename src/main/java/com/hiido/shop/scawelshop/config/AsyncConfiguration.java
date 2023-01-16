package com.hiido.shop.scawelshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程配置类
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration {
    private int corePoolSize = Runtime.getRuntime().availableProcessors() - 1;

    private int myCorePoolSize = 50;

    private int myMaxPoolSize = 100;

    private int myQueueCapacity = 200;

    @Bean("threadValidateAllLink")
    public Executor doSomethingImage() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数目
        executor.setCorePoolSize(myCorePoolSize);
        // 最大线程数目
        executor.setMaxPoolSize(myMaxPoolSize);
        // 队列容量
        executor.setQueueCapacity(myQueueCapacity);
        executor.setKeepAliveSeconds(60);
        // 子线程名前缀
        executor.setThreadNamePrefix("threadValidateAllLink-");
        // 拒绝策略启动主线程处理
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置任务完成关闭线程
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}
