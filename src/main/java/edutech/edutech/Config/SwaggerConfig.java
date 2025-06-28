package edutech.edutech.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api 2025 Creacion de Usuario")
                        .version("1.2")
                        .description("Documentacion de la API para la Creacion de Usuarios "));
    }

}
