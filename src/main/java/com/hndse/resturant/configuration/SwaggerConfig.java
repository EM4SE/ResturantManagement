package com.hndse.resturant.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTurant")
                .version("1.0")
                        .description("RESTurant")
                );
    }
}
