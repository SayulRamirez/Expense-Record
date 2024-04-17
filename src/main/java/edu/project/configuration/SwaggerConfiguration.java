package edu.project.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Registro de gastos",
                summary = "Esta aplicación tiene como objetivo el proporcionar una API para operaciones básicas de los gastos",
                contact = @Contact( name = "Saúl Rámirez", url = "github.com/SayulRamirez", email = "sayulramirez@gmail.com"),
                version = "v1.0.0"
        )
)
public class SwaggerConfiguration {
}
