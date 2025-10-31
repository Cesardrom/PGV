package com.comandos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.comandos.controllers.CliControllers;

/**
 * Clase principal de la aplicación Spring Boot para la gestión de procesos y servicios.
 * Esta clase inicia la aplicación y configura un CommandLineRunner para ejecutar el menú de consola.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class ProcesosServiciosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcesosServiciosApplication.class, args);
    }
    @Bean
    CommandLineRunner demo(CliControllers procesos) {
        return args -> {
            System.out.println("Iniciando proceso al arrancar la aplicación...");

            procesos.menuConsola();

            System.out.println("Proceso finalizado.");
        };
    }
}
