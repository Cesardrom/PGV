package servicios;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.servicios.MilleniumFalcon;

/**
 * Clase de pruebas unitarias para MilleniumFalcon, verificando que termine con escape o destrucción.
 * @author Cesardrom
 */
public class MilleniumFalconTest {
    
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
    public void testMilleniumFalcon_finalizaConEscapeODestruccion() throws InterruptedException {
        MilleniumFalcon m = new MilleniumFalcon();
        
        m.iniciar();
        
        String salida = outputStream.toString();
        
        boolean contieneDestruye = salida.contains("se destruye");
        boolean contieneEscapan = salida.contains("escapan");
        
        assertTrue(contieneDestruye ^ contieneEscapan, 
                   "Debe haber exactamente un resultado: escape o destrucción");
    }
}
