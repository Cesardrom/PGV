package com.docencia.hilos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServidorMazmorras {

    /**
     * Clase interna que representa una petición de entrada a una mazmorra.
     * Implementa Runnable para ser ejecutada en un hilo separado.
     */
    static class PeticionMazmorra implements Runnable {
        private final String nombreJugador;
        private final String mazmorra;

        /**
         * Constructor de la petición de mazmorra.
         * 
         * @param nombreJugador Nombre del jugador que solicita la mazmorra
         * @param mazmorra      Nombre de la mazmorra solicitada
         */
        public PeticionMazmorra(String nombreJugador, String mazmorra) {
            this.nombreJugador = nombreJugador;
            this.mazmorra = mazmorra;
        }

        /**
         * Método que ejecuta la petición de mazmorra.
         * Simula los pasos de validación, preparación y carga de una mazmorra.
         */
        @Override
        public void run() {
            String hilo = Thread.currentThread().getName();
            System.out.println("[" + hilo + "] Validando al jugador " + nombreJugador);
            try {
                Thread.sleep(500); // Simula el tiempo de validación del jugador
            } catch (InterruptedException e) {
                System.out.println("[" + hilo + "] Validación interrumpida para " + nombreJugador);
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("[" + hilo + "] Preparando mazmorra '" + mazmorra +
                    "' para el jugador " + nombreJugador);
            try {
                Thread.sleep(1000 + (int) (Math.random() * 1000)); // Simula la preparación de la mazmorra
            } catch (InterruptedException e) {
                System.out.println("[" + hilo + "] Petición de " + nombreJugador + " interrumpida");
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("[" + hilo + "] Cargando enemigos y loot en '" + mazmorra + "'");
            try {
                Thread.sleep(500 + (int) (Math.random() * 500)); // Simula la carga final de contenido
            } catch (InterruptedException e) {
                System.out.println("[" + hilo + "] Carga interrumpida para " + nombreJugador);
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("[" + hilo + "] Mazmorra '" + mazmorra +
                    "' lista para " + nombreJugador + " 🎮");
        }
    }

    public static void main(String[] args) {

        ExecutorService gmBots = Executors.newFixedThreadPool(3); // Creamos un pool de 3 GM bots

        // Simulamos 10 jugadores que quieren entrar a mazmorras
        String[] jugadores = {
                "Link", "Zelda", "Geralt", "Yennefer", "Gandalf",
                "Frodo", "Aragorn", "Leia", "Luke", "DarthVader"
        };
        String[] mazmorras = {
                "Catacumbas de Hyrule", "Torre Oscura", "Moria",
                "Estrella de la Muerte", "Nido de Dragón"
        };

        for (int i = 0; i < jugadores.length; i++) {
            String jugador = jugadores[i];
            String dungeon = mazmorras[i % mazmorras.length];
            gmBots.execute(new PeticionMazmorra(jugador, dungeon));
        }

        gmBots.shutdown();
        try {
            if (!gmBots.awaitTermination(10, TimeUnit.SECONDS)) {
                gmBots.shutdownNow();
            }
        } catch (InterruptedException e) {
            gmBots.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Servidor: todas las peticiones han sido procesadas por los GM bots.");
    }
}
