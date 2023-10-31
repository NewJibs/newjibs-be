package com.ssafy.newjibs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final String version = "v1";
    private final String title = "NewJibs API " + version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(version).select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.newjibs"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description("<h3>부동산 정보 API</h3> Swagger를 이용한 API")
                .contact(new Contact("takealook", "https://github.com/takealook97", "takealook97@naver.com")).license("takealook License")
                .licenseUrl("NewJibs All rights reserved").version("1.0").build();
    }
}
