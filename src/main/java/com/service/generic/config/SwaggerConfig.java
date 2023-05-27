package com.service.generic.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@OpenAPIDefinition(
        info = @Info(
                title = "Generic Service API",
                version = "0.0.1",
                description = "Generic project for Spring Boot",
                contact = @Contact(
                        name = "{Manager contact}",
                        url = "{Support link}",
                        email = "{Support email}"
                ),
                license = @License(
                        name = "{license name}",
                        url = "{license url}"))
)
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

}
