package com.comandos.services.impl;

import org.springframework.stereotype.Service;

import com.comandos.domain.Job;
import com.comandos.services.abstracts.ComandoServiceAbstract;
import com.comandos.services.interfaces.TopService;

/**
 * Implementación del servicio para el comando Top.
 * Extiende ComandoServiceAbstract e implementa TopService para manejar la ejecución del comando top.
 *
 * @author Cesardrom
 * @version 1.0
 * @since 1.0
 */
@Service
public class TopServiceImpl extends ComandoServiceAbstract implements TopService{

    public TopServiceImpl(){
        this.setTipo(Job.TOP);
        this.setValidation("-bn([0-9])?");
    }

    @Override
    public void executeCommand(String[] args) {
        String commandLine = String.join(" ", args);
        procesarLinea(commandLine);
    }
}
