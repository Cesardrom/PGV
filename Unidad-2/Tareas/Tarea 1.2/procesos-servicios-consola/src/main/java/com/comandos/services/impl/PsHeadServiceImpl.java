package com.comandos.services.impl;

import org.springframework.stereotype.Service;

import com.comandos.domain.Job;
import com.comandos.services.abstracts.ComandoServiceAbstract;
import com.comandos.services.interfaces.PsHeadService;

/**
 * Implementación del servicio para el comando Ps aux | head.
 * Extiende ComandoServiceAbstract e implementa PsHeadService para manejar la ejecución del comando ps aux | head.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
@Service
public class PsHeadServiceImpl extends ComandoServiceAbstract implements PsHeadService {
    public PsHeadServiceImpl(){
        this.setTipo(Job.PS);
    }

    @Override
    public void executeCommand(String[] args) {
        String commandLine = String.join(" ", args);
        procesarLinea(commandLine);
    }
}
