package servicios;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.servicios.Quidditch;

/**
 * Clase de pruebas unitarias para Quidditch, verificando que termine cuando se atrape la Snitch.
 * @author Cesardrom
 */
public class QuidditchTest {
    
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
    public void testQuidditch_terminaCuandoSnitchAtrapada() throws InterruptedException {
        Quidditch q = new Quidditch();
        
        q.iniciar();
        
        String salida = outputStream.toString();
        
        assertTrue(salida.contains("¡Snitch dorada atrapada!"));
        assertTrue(salida.contains("Marcador final:"));
    }
}
