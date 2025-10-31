package com.comandos.services.abstracts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.comandos.domain.Job;
import com.comandos.repositories.file.FileJobRepository;

/**
 * Clase abstracta base para servicios de comandos.
 * Proporciona funcionalidad común para ejecutar procesos, validar comandos y persistir salidas.
 *
 * @author Cesardrom
 * @version 1.0
 * @since 1.0
 */
public abstract class ComandoServiceAbstract {
    private String comando;
    private Job tipo;
    private String validation;
    protected List<String> parametrosValidos;

    public ComandoServiceAbstract(String... parametrosValidos) {
        this.parametrosValidos = Arrays.asList(parametrosValidos);
    }

    FileJobRepository fileRepository;

    public FileJobRepository getFileRepository() {
        return fileRepository;
    }

    @Autowired
    public void setFileRepository(FileJobRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Job getTipo() {
        return tipo;
    }

    public void setTipo(Job tipo) {
        this.tipo = tipo;
    }

    public void procesarLinea(String linea) {
        String[] arrayComando = linea.split("\\s+");
        this.setComando(arrayComando[0]);
        if (!validar(arrayComando)) {
            return;
        }

        Process proceso;
        try {
            proceso = new ProcessBuilder("sh", "-c", linea).start();
            ejecutarProceso(proceso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ejecutarProceso(Process proceso) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StringBuilder output = new StringBuilder();
        StringBuilder errorOutput = new StringBuilder();

        executor.submit(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[OUT] " + line);
                    output.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[ERR] " + line);
                    errorOutput.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            proceso.waitFor();
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fullOutput = output.toString() + errorOutput.toString();
        if (fileRepository != null) {
            fileRepository.saveOutput(this.tipo, fullOutput);
        }

        return true;
    }

    public String getTipoToString() {
        if (tipo == null) {
            return null;
        }
        return tipo.toString();
    }

    public boolean validar(String[] arrayComando) {
        if (!validarComando()) {
            return false;
        }
        if (validation != null && !validation.isEmpty()) {
            Pattern pattern = Pattern.compile(validation);
            for (int i = 1; i < arrayComando.length; i++) {
                String parametro = arrayComando[i];
                if (parametro.startsWith("-")) {
                    Matcher matcher = pattern.matcher(parametro);
                    if (!matcher.find()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validarComando() {
        if (!this.getComando().toUpperCase().equals(getTipoToString())) {
            System.out.println("Comando invalido");
            return false;
        }
        return true;
    }
}
