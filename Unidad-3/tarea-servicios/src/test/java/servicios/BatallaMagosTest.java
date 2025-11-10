package servicios;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.servicios.BatallaMagos;

/**
 * Clase de pruebas unitarias para BatallaMagos, verificando que la batalla termine con un ganador.
 * @author Cesardrom
 */
public class BatallaMagosTest {

    final PrintStream originalOut = System.out;
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    public void testBatallaMagos_debeHaberGanadorYTerminar() throws InterruptedException {
        BatallaMagos b = new BatallaMagos();

        b.iniciar();

        String salida = outputStream.toString();
        assertTrue(salida.contains("gana la batalla mágica."));
    }
}
