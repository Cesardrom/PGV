package servicios;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.servicios.FabricaDroids;

/**
 * Clase de pruebas unitarias para FabricaDroids, verificando que los droids se ensamblen antes de activarse y la cuenta sea correcta.
 * @author Cesardrom
 */
public class FabricaDroidsTest {
    
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
    public void testFabricaDroids_noSeActivaAntesDeEnsamblar_yCuentaCorrecta() throws InterruptedException {
        // Arrange
        FabricaDroids fab = new FabricaDroids();
        
        // Act
        fab.iniciar();
        
        // Assert
        String salida = outputStream.toString();
        
        // Verificar que cada droid fue ensamblado antes de ser activado
        for (int k = 1; k <= fab.N; k++) {
            String droidId = "Droid-" + k;
            int idxE = salida.indexOf("Ensamblado " + droidId);
            int idxA = salida.indexOf("Activado " + droidId);
            
            assertTrue(idxE != -1, "Droid-" + k + " debe ser ensamblado");
            assertTrue(idxA != -1, "Droid-" + k + " debe ser activado");
            assertTrue(idxE < idxA, "Droid-" + k + " debe ensamblarse antes de activarse");
        }
        
        // Verificar cuenta final
        assertEquals(fab.N, fab.activados.get());
    }
}
