package com.DaoVsDTO.configuration;


//Configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import java.net.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "API CLUBES",
                description = "Our App provides a concise lisiting of football team names",
                termsOfService = "www.zuluaga.com/termsOfService",
                version = "1.0.0",
                contact = @Contact(
                        name = "Carlos Andrés Zuluaga",
                        url = "www.carloszuluaga.com/contact",
                        email = "zuluaga@gmail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Carlos Zuluaga",
                        url = "www.carloszuluaga.com/license"
                )
        ),
        servers = {
                @Server(
                        description = "PROD SERVER",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description="Dev Server",
                        url = "http://carloszuluaga:8080"
                )

        },
        security = @SecurityRequirement(
                name= "Security Token"
        )
)

@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)

public class SwaggerConfig  {}
