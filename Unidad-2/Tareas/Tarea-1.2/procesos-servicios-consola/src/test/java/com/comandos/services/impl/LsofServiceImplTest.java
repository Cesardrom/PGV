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

class LsofServiceImplTest {

    private LsofServiceImpl lsofService;

    @Mock
    private FileJobRepository fileJobRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lsofService = new LsofServiceImpl() {
            @Override
            public boolean ejecutarProceso(Process proceso) {
                return true;
            }
        };
        lsofService.setFileRepository(fileJobRepository);
    }


    @Test
    void testProcesarLineaComandoValidoSinParametros() {
        String linea = "LSOF";
        lsofService.procesarLinea(linea);
        assertEquals("LSOF", lsofService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testProcesarLineaComandoValidoConParametros() {
        String linea = "LSOF -i";
        lsofService.procesarLinea(linea);
        assertEquals("LSOF", lsofService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testProcesarLineaComandoInvalido() {
        String linea = "INVALID";
        lsofService.procesarLinea(linea);
        assertEquals("INVALID", lsofService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testValidarComandoValido() {
        lsofService.setComando("LSOF");
        assertTrue(lsofService.validarComando());
    }

    @Test
    void testValidarComandoInvalido() {
        lsofService.setComando("INVALID");
        assertFalse(lsofService.validarComando());
    }


    @Test
    void testExecuteCommand() {
        String[] args = {"LSOF", "-i"};
        lsofService.executeCommand(args);
        assertEquals("LSOF", lsofService.getComando());
    }
}
