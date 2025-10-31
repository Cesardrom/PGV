package com.comandos.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import com.comandos.services.interfaces.LsofService;
import com.comandos.services.interfaces.PsHeadService;
import com.comandos.services.interfaces.TopService;

class CliControllersTest {

    private CliControllers cliControllers;

    @Mock
    private LsofService lsofService;

    @Mock
    private PsHeadService psHeadService;

    @Mock
    private TopService topService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliControllers = new CliControllers();
        cliControllers.lsofService = lsofService;
        cliControllers.psHeadService = psHeadService;
        cliControllers.topService = topService;
    }

    @Test
    void testMenuConsolaLsofCommand() {
        String input = "LSOF -i\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(lsofService).executeCommand(new String[]{"LSOF", "-i"});
        verify(psHeadService, never()).executeCommand(any());
        verify(topService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaTopCommand() {
        String input = "TOP -b\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(topService).executeCommand(new String[]{"TOP", "-b"});
        verify(lsofService, never()).executeCommand(any());
        verify(psHeadService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaPsCommand() {
        String input = "PS aux\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(psHeadService).executeCommand(new String[]{"PS", "aux"});
        verify(lsofService, never()).executeCommand(any());
        verify(topService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaInvalidCommand() {
        String input = "INVALID\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(lsofService, never()).executeCommand(any());
        verify(psHeadService, never()).executeCommand(any());
        verify(topService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaCaseInsensitiveLsof() {
        String input = "lsof\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(lsofService).executeCommand(new String[]{"lsof"});
        verify(psHeadService, never()).executeCommand(any());
        verify(topService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaCaseInsensitiveTop() {
        String input = "top\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(topService).executeCommand(new String[]{"top"});
        verify(lsofService, never()).executeCommand(any());
        verify(psHeadService, never()).executeCommand(any());
    }

    @Test
    void testMenuConsolaCaseInsensitivePs() {
        String input = "ps\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliControllers.menuConsola();

        verify(psHeadService).executeCommand(new String[]{"ps"});
        verify(lsofService, never()).executeCommand(any());
        verify(topService, never()).executeCommand(any());
    }
}
