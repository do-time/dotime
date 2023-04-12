package com.jhd.dotime.common.config;


import com.jhd.dotime.common.annotation.CurrentMember;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class Swagger2Config{


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(CurrentMember.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());



    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Do Time REST API",
                "This Do Time API.",
                "V1",
                "Terms of service",
                new Contact("administrator", "www.example.com", "administrator@email.com"),
                "License of API", "www.example.com", Collections.emptyList());
    }
}
