package servicios;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.servicios.SaiyanRace;

/**
 * Clase de pruebas unitarias para SaiyanRace, verificando que uno de los Saiyans gane la carrera.
 * @author Cesardrom
 */
public class SaiyanRaceTest {

    @Test
    public void testSaiyanRace() throws InterruptedException {
        // Redirigir la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Ejecutar los hilos
        Thread goku = new Thread(new SaiyanRace("Goku"));
        Thread vegeta = new Thread(new SaiyanRace("Vegeta"));

        goku.start();
        vegeta.start();

        goku.join();   // Esperar a que termine Goku
        vegeta.join(); // Esperar a que termine Vegeta

        // Verificar si uno de ellos ganó
        String output = outContent.toString();
        assertTrue(output.contains("Goku ha ganado la carrera!") || output.contains("Vegeta ha ganado la carrera!"));
    }
}
