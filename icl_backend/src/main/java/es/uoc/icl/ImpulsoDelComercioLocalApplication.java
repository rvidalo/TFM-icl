package es.uoc.icl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ImpulsoDelComercioLocalApplication {

	public static void main(String[] args) {
		try {
            // Cargar el archivo .env
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            dotenv.entries().forEach(entry -> {
                System.setProperty(entry.getKey(), entry.getValue());
            });
        } catch (Exception e) {
            System.err.println("No se encontró el archivo .env, usando variables de entorno del sistema");
        }

        // Iniciar la aplicación Spring Boot
		SpringApplication.run(ImpulsoDelComercioLocalApplication.class, args);
	}

}
