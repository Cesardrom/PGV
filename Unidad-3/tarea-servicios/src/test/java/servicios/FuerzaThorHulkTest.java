package servicios;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.servicios.FuerzaThorHulk;

/**
 * Clase de pruebas unitarias para FuerzaThorHulk, verificando que termine por tiempo y declare un resultado.
 * @author Cesardrom
 */
public class FuerzaThorHulkTest {
    
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testFuerzaThorHulk_terminaPorTiempo_yDeclaraResultado() throws InterruptedException {
        FuerzaThorHulk f = new FuerzaThorHulk();
        
        f.iniciar();
        
        String salida = outputStream.toString();
        
        assertTrue(salida.contains("¡Tiempo!"));
        assertTrue(salida.contains("gana") || salida.contains("Empate"));
    }
}
