package com.comandos.repositories.interfaces;

import java.nio.file.Path;

import com.comandos.domain.Job;

/**
 * Interfaz para el repositorio de trabajos (jobs).
 * Define métodos para agregar texto, obtener la ruta del archivo y guardar la salida de un trabajo.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
public interface IJobRepository {
    public boolean add(String texto);
    public Path getpath();
    public boolean saveOutput(Job job, String output);
}
