package com.comandos.controllers;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comandos.services.interfaces.LsofService;
import com.comandos.services.interfaces.PsHeadService;
import com.comandos.services.interfaces.TopService;

/**
 * Controlador de línea de comandos para la gestión de procesos y servicios.
 * Esta clase maneja la interacción con el usuario a través de la consola, permitiendo ejecutar comandos como Top, Lsof y PsAux | head.
 *
 * @author [Tu Nombre]
 * @version 1.0
 * @since 1.0
 */
@Service
public class CliControllers {
    @Autowired
    LsofService lsofService;
    @Autowired
    PsHeadService psHeadService;
    @Autowired
    TopService topService;

    public void menuConsola(){
        Scanner scanner = new Scanner (System.in);
        System.out.println("===Lanzador de Procesos (CLI)===\n" +
                "Comandos:\n" +
                "  Top\n" +
                "  Lsof\n" +
                "  PsAux | head \n"
                );
        String comando = scanner.nextLine();
        if (comando.toUpperCase().startsWith("LSOF")){
            lsofService.executeCommand(comando.split("\\s+"));
        }
        if (comando.toUpperCase().startsWith("TOP")){
            topService.executeCommand(comando.split("\\s+"));
        }
        if (comando.toUpperCase().startsWith("PS")){
            psHeadService.executeCommand(comando.split("\\s+"));
        }
    }
}
