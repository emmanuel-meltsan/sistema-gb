package com.emmlg.biblioapi.util;


import io.swagger.v3.oas.models.OpenAPI;
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
                        .description("API para manejar libros en la biblioteca"));
    }
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("API Personalizada")
//                        .version("1.0")
//                        .description("Documentación de la API con Swagger"))
//                .servers(List.of(
//                        new Server().url("http://localhost:8080").description("Servidor Local"),
//                        new Server().url("https://api.miapp.com").description("Servidor de Producción")
//                ));
//    }
}
