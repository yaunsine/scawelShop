package com.hiido.shop.scawelshop;

import com.hiido.shop.scawelshop.controller.SpiderController;
import com.hiido.shop.scawelshop.service.IProductService;
import com.hiido.shop.scawelshop.service.impl.*;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("com.hiido.shop.scawelshop.mapper")
public class ScawelShopApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    @Autowired
    private IProductService productService;

    @Resource
    private CjSpiderRestApiAdvertiserProcessor cjSpiderRestAPIAdvertiserProcessor;

    @Resource
    private CjSpiderGraphQLAdvertiserProcessor cjSpiderGraphQLAdvertiserProcessor;

    @Resource
    private CjSpiderGraphqlProductProcessor cjSpiderGraphqlProductProcessor;

    @Resource
    private ValidateAllLinkPageProcess validateAllLinkPageProcess;

    @Resource
    private AsyncValidateAllLink asyncValidateAllLink;

    public static void main(String[] args) {

        SpringApplication.run(ScawelShopApplication.class, args);
    }


    /**
     * TODO: springboot启动webMagic爬虫业务
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("执行爬虫");
        logger.info("\n __      __      ___.                          .__        \n" +
                "/  \\    /  \\ ____\\_ |__   _____ _____     ____ |__| ____  \n" +
                "\\   \\/\\/   // __ \\| __ \\ /     \\\\__  \\   / ___\\|  |/ ___\\ \n" +
                " \\        /\\  ___/| \\_\\ \\  Y Y  \\/ __ \\_/ /_/  >  \\  \\___ \n" +
                "  \\__/\\  /  \\___  >___  /__|_|  (____  /\\___  /|__|\\___  >\n" +
                "       \\/       \\/    \\/      \\/     \\//_____/         \\/ ");
        // productService.getList();
        // productService.spiderHttpClient();

//        cjSpiderRestAPIAdvertiserProcessor.start();
         asyncValidateAllLink.batchAllLink();
//         System.out.println("爬虫执行完毕.......");
    }
}
