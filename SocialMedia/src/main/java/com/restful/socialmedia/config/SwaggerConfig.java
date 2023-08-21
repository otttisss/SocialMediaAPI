package com.restful.socialmedia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// url http://localhost:8080/swagger-ui/index.html
@Configuration
@OpenAPIDefinition (
        info = @Info (
                title = "Social Media API",
                version = "1.0",
                description = "Implementation of RESTful API",
                contact = @Contact(
                        name = "Efim",
                        email = "efimotis@gmail.com"
                )
        )
)
public class SwaggerConfig implements WebMvcConfigurer {

}
