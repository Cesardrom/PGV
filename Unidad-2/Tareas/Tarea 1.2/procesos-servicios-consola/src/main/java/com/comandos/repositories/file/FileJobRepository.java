package com.comandos.repositories.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.comandos.domain.Job;
import com.comandos.repositories.interfaces.IJobRepository;

/**
 * Implementación del repositorio de trabajos basada en archivos.
 * Esta clase maneja la persistencia de datos en archivos, permitiendo agregar texto y guardar salidas de comandos.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
@Repository
public class FileJobRepository implements IJobRepository {
    String fileName;
    static Path path;
    private static Logger logger = LoggerFactory.getLogger(IJobRepository.class);

    public FileJobRepository(){
        if (fileName==null){
            fileName = "mis_procesos.txt";
        }

        // Use src/main/resources as base directory
        path = Paths.get("src/main/resources", fileName);
    }

    @Override
    public boolean add(String texto) {
        try {
            Files.write(path, texto.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            logger.error("Ha habido un error al guardar en el fichero", e);
            return false;
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Path getpath() {
        return path;
    }

    @Override
    public boolean saveOutput(Job job, String output) {
        String fileName = job.toString().toLowerCase() + "_output.txt";
        Path outputPath = Paths.get("src/main/resources", fileName);
        try {
            Files.write(outputPath, output.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            return true;
        } catch (IOException e) {
            logger.error("Error saving output to file: " + fileName, e);
            return false;
        }
    }
}
