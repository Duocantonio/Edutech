package EDU_TECH.Edu_tech.config;

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
                        .title("Api 2025 Creacion de Curso , Evaluaciones Y reportes de errores")
                        .version("1.2")
                        .description("Documentacion de las APIs para los sistemas de Cursos, Evaluaciones y Reportes"));
    }

}
