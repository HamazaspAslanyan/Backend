package com.example.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                )
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Taxpayer Service API")
                .description("API for Taxpayers")
                .version("1.0")
                .license(apiLicence())
                .contact(apiContact());
    }

    private License apiLicence() {
        return new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");
    }

    private Contact apiContact() {
        return new Contact()
                .name("IU Networks")
                .url("https://www.iunetworks.am/")
                .email("info@iunetworks.am");
    }

}
