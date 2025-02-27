package com.emmlg.biblioapi.util;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book API")
                        .version("1.0")
                        .description("API para manejar libros en la biblioteca").
                        contact(
                                new Contact()
                                        .name("emmanuel lopez")
                                        .email("emmanuel.lopez@meltsan.com")

                        ));
    }
}
