package com.docencia.hilos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Clase principal que simula el cálculo de daño crítico en un juego de
 * mazmorras.
 * Utiliza hilos para calcular el daño de múltiples ataques de manera
 * concurrente.
 */
public class CalculadoraDanoCritico {

    /**
     * Clase interna que representa un ataque con sus propiedades.
     */
    static class Ataque {
        final String atacante; // Nombre del personaje que realiza el ataque
        final int danoBase; // Daño base del ataque
        final double probCritico; // Probabilidad de que el ataque sea crítico (ej. 0.25 = 25%)
        final double multiplicadorCritico; // Multiplicador aplicado si es crítico (ej. 2.0 = x2)

        /**
         * Constructor de la clase Ataque.
         * 
         * @param atacante             Nombre del atacante
         * @param danoBase             Daño base del ataque
         * @param probCritico          Probabilidad de crítico
         * @param multiplicadorCritico Multiplicador si es crítico
         */
        Ataque(String atacante, int danoBase, double probCritico, double multiplicadorCritico) {
            this.atacante = atacante;
            this.danoBase = danoBase;
            this.probCritico = probCritico;
            this.multiplicadorCritico = multiplicadorCritico;
        }
    }

    /**
     * Clase interna que representa una tarea para calcular el daño de un ataque.
     * Implementa Callable para devolver el daño calculado.
     */
    static class TareaCalcularDano implements Callable<Integer> {
        private final Ataque ataque;

        /**
         * Constructor de la tarea de cálculo de daño.
         * 
         * @param ataque El ataque cuya daño se va a calcular
         */
        TareaCalcularDano(Ataque ataque) {
            this.ataque = ataque;
        }

        /**
         * Método que ejecuta la tarea de cálculo de daño.
         * Simula el cálculo determinando si es crítico y aplicando el multiplicador
         * correspondiente.
         * 
         * @return El daño final calculado
         * @throws Exception Si ocurre un error durante la ejecución
         */
        @Override
        public Integer call() throws Exception {
            String hilo = Thread.currentThread().getName();
            System.out.println("[" + hilo + "] Calculando daño para " + ataque.atacante);

            // Determina si el ataque es crítico basado en la probabilidad
            boolean esCritico = Math.random() < ataque.probCritico;
            double multiplicador = esCritico ? ataque.multiplicadorCritico : 1.0;

            // Simula tiempo de procesamiento
            Thread.sleep(500 + (int) (Math.random() * 500));

            // Calcula el daño final aplicando el multiplicador
            int danoFinal = (int) (ataque.danoBase * multiplicador);
            System.out.println("[" + hilo + "] " + ataque.atacante +
                    (esCritico ? " ¡CRÍTICO!" : " golpe normal") +
                    " -> daño: " + danoFinal);

            return danoFinal;
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futuros = new ArrayList<>();

        // Simulamos una raid
        Ataque[] ataques = {
                new Ataque("Mago del Fuego", 120, 0.30, 2.5),
                new Ataque("Guerrero", 150, 0.15, 2.0),
                new Ataque("Pícaro", 90, 0.50, 3.0),
                new Ataque("Arquera Élfica", 110, 0.35, 2.2),
                new Ataque("Invocador", 80, 0.40, 2.8),
                new Ataque("Paladín", 130, 0.10, 1.8),
                new Ataque("Bárbaro", 160, 0.20, 2.1),
                new Ataque("Nigromante", 100, 0.25, 2.3),
        };

        // Enviamos las tareas al pool y mostramos diferencia con execute() sin valor
        // retorno
        System.out.println("Demostración de submit(Callable) que devuelve Future:");
        for (Ataque ataque : ataques) {
            Future<Integer> futuro = pool.submit(new TareaCalcularDano(ataque));
            futuros.add(futuro);
        }

        // Recogemos resultados
        int totalRaid = 0;
        for (int i = 0; i < ataques.length; i++) {
            try {
                int dano = futuros.get(i).get();
                totalRaid += dano;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Lectura de resultado interrumpida");
            } catch (ExecutionException e) {
                System.out.println("Error calculando daño: " + e.getCause());
            }
        }

        System.out.println("Daño total de la raid: " + totalRaid);

        pool.shutdown();
    }
}
