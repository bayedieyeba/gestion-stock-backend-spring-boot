package com.baye.gestiondestock.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;


@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Baye Dieye BA",
                        email="contact@bayedieyeba.com",
                        url = "https://www.bayedieyeba.com"
                ),
                description = "Documentation de l'API Gestion de stock",
                title = "Gestion de stock",
                version = "v1",
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terme of service"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "Local ENV",
                        url = "http://localhost:8081"
                ),
                @io.swagger.v3.oas.annotations.servers.Server(
                        description = "Prod ENV",
                        url = "http://localhost:8081"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {


/*    private String devUrl = "localhost:8081";


    private String prodUrl="localhost:8081";
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("bayedieyeba@esp.sn");
        contact.setName("Baye Dieye BA");
        contact.setUrl("https://www.baye.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Gestion de stock  REST API")
                .version("v1")
                .contact(contact)
                .description("Gestion de stock API documentation").termsOfService("https://www.baye.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));

    }*/

    /*public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Gestion de stock API documentation")
                                .title("Gestion de stock  REST API")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baye.gestiondestock"))
                .paths(PathSelectors.ant(APP_ROOT + "/**"))
                .build();
    }*/

   /* @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baye.gestiondestock"))
                .paths(PathSelectors.ant(APP_ROOT +"/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Gestion de stock  REST API")
                .description("Gestion de stock  REST API")
                .version("v1")
                .build();
    }*/
}
