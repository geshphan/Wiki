package com.yemenshi.geshphan.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("API")
                        .description("API application")
                        .contact(new Contact().name("name"))
                );
    }

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("api-version-1")
                .pathsToMatch("/**")    // 처리될 url
                .pathsToExclude("/magic/**", "/test/**")   // 제외 url
                .build();
    }
}
