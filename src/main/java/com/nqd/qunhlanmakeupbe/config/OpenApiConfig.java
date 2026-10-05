package com.nqd.qunhlanmakeupbe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quỳnh Lan Makeup API Documentation")
                        .version("1.0.0")
                        .description("RESTful API services for Quỳnh Lan Makeup application")
                        .contact(new Contact()
                                .name("Quỳnh Lan Makeup Team")
                                .email("contact@quynhlanmakeup.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
