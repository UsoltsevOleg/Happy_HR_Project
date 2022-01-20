package com.example.happy.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
    Конфигурация Swagger для генерации html с описанием API
    Сваггер генерирует документацию к api На основе контроллеров (html)
    localhost:8080/swagger-ui.html
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.happy.hr.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
}
