package com.hiido.shop.scawelshop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.hiido.shop.scawelshop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("爬虫项目接口").description("爬虫项目接口v1").termsOfServiceUrl("").version("1.0").build();
    }

    private List<Parameter> setHeaderToken() {
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(null);
        return parameterList;
    }
}
