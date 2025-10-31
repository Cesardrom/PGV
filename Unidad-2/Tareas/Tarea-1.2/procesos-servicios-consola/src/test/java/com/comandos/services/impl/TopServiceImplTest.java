package com.comandos.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.comandos.repositories.file.FileJobRepository;

class TopServiceImplTest {

    private TopServiceImpl topService;

    @Mock
    private FileJobRepository fileJobRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        topService = new TopServiceImpl() {
            @Override
            public boolean ejecutarProceso(Process proceso) {
                return true;
            }
        };
        topService.setFileRepository(fileJobRepository);
    }



    @Test
    void testProcesarLineaComandoValidoConParametroValido() {
        String linea = "TOP -b";
        topService.procesarLinea(linea);
        assertEquals("TOP", topService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testProcesarLineaComandoValidoSinParametro() {
        String linea = "TOP";
        topService.procesarLinea(linea);
        assertEquals("TOP", topService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testProcesarLineaComandoValidoConParametroInvalido() {
        String linea = "TOP -invalid";
        topService.procesarLinea(linea);
        assertEquals("TOP", topService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testValidarComandoValido() {
        topService.setComando("TOP");
        assertTrue(topService.validarComando());
    }

    @Test
    void testValidarComandoInvalido() {
        topService.setComando("INVALID");
        assertFalse(topService.validarComando());
    }



    @Test
    void testExecuteCommand() {
        String[] args = {"TOP", "-b"};
        topService.executeCommand(args);
        assertEquals("TOP", topService.getComando());
    }
}
