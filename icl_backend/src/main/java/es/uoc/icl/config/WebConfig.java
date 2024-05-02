package es.uoc.icl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las URL
                .allowedOrigins("*") // Permite solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos los métodos HTTP
                .allowedHeaders("*"); // Permite todas las cabeceras
    }
}
