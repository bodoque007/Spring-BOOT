package com.bodoque007.RESTAPI.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Example REST API",
                description = "OpenAPI documentation for simple REST API. Note that, because of a Spring Docs technicality, an extra employee-controller is being shown, providing useless information",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:" + "${server.port}"
                )
        }
)
public class OpenApiConfig {
}
