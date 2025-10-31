package com.comandos.services.impl;

import org.springframework.stereotype.Service;

import com.comandos.domain.Job;
import com.comandos.services.abstracts.ComandoServiceAbstract;
import com.comandos.services.interfaces.LsofService;

/**
 * Implementación del servicio para el comando Lsof.
 * Extiende ComandoServiceAbstract e implementa LsofService para manejar la ejecución del comando lsof.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
@Service
public class LsofServiceImpl extends ComandoServiceAbstract implements LsofService {

    public LsofServiceImpl(){
        this.setTipo(Job.LSOF);
        this.setValidation("");
    }

    @Override
    public void executeCommand(String[] args) {
        String commandLine = String.join(" ", args);
        procesarLinea(commandLine);
    }
}
