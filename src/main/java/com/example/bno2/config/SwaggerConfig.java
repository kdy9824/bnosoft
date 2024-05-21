package com.example.bno2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public static final Info BNO_BACKEND_API_INFO = new Info()
            .title("타이틀")
            .description("설명")
            .version("v0.0.1");

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(BNO_BACKEND_API_INFO);
    }

}