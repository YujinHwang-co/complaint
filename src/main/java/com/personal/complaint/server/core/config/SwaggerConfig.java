package com.personal.complaint.server.core.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(type = SecuritySchemeType.APIKEY, name = "Authorization", in = SecuritySchemeIn.HEADER)
@Configuration
public class SwaggerConfig {

    Server server = new Server().url("/");

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .addServersItem(server);
    }

    @Bean
    public GroupedOpenApi testGroup1(){
        /**
         * pathsToMatch example
         * pathsToMatch("/api/test/**","/api/auth/**")
         */
        return GroupedOpenApi.builder()
                .group("swagger-api-test")
                .packagesToScan("com.personal.complaint.server.api")
                .pathsToMatch("/api/**")
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title("Complaint Service")
                .description("Api Swagger Test Page")
                .version("1.0.0");
    }
}
