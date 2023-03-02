package com.ss.swagger.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ss"))
				.paths(PathSelectors.any()).build().apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfo("User Registration :String Boot", "this project is developed by ss teams", "1.0", "Terms of service",
				new Contact("Sculpt soft", "https://www.sculptsoft.com/", "info@sculptsoft.com"), "License APIS",
				"APIS", Collections.EMPTY_LIST);
	}

}
