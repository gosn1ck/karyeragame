package ru.karyeragame.authservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server(url = "http://127.0.0.1:9009")}, info = @Info(title = "Auth Service",
        description = "Some long and useful description", version = "v1"))
public class OpenApiConfig {
}
