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

import com.comandos.domain.Job;
import com.comandos.repositories.file.FileJobRepository;

class PsHeadServiceImplTest {

    private PsHeadServiceImpl psHeadService;

    @Mock
    private FileJobRepository fileJobRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        psHeadService = new PsHeadServiceImpl() {
            @Override
            public boolean ejecutarProceso(Process proceso) {
                return true;
            }
        };
        psHeadService.setFileRepository(fileJobRepository);
    }

    @Test
    void testConstructorSetsTipoAndValidation() {
        assertEquals(Job.PS, psHeadService.getTipo());
        assertEquals("aux|ef", psHeadService.getValidation());
    }

    @Test
    void testProcesarLineaComandoValidoSinParametros() {
        String linea = "PS";
        psHeadService.procesarLinea(linea);
        assertEquals("PS", psHeadService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any()); // No output since process not executed
    }

    @Test
    void testProcesarLineaComandoValidoConParametros() {
        String linea = "PS aux";
        psHeadService.procesarLinea(linea);
        assertEquals("PS", psHeadService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testProcesarLineaComandoInvalido() {
        String linea = "INVALID";
        psHeadService.procesarLinea(linea);
        assertEquals("INVALID", psHeadService.getComando());
        verify(fileJobRepository, never()).saveOutput(any(), any());
    }

    @Test
    void testValidarComandoValido() {
        psHeadService.setComando("PS");
        assertTrue(psHeadService.validarComando());
    }

    @Test
    void testValidarComandoInvalido() {
        psHeadService.setComando("INVALID");
        assertFalse(psHeadService.validarComando());
    }

    @Test
    void testExecuteCommand() {
        String[] args = {"PS", "aux"};
        psHeadService.executeCommand(args);
        assertEquals("PS", psHeadService.getComando());
    }
}
