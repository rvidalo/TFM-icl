package es.uoc.icl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ImpulsoDelComercioLocalApplication {

	public static void main(String[] args) {
		// Cargar el archivo .env
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });

        // Iniciar la aplicación Spring Boot
		SpringApplication.run(ImpulsoDelComercioLocalApplication.class, args);
	}

}
