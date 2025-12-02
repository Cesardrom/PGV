package com.docencia.hilos;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SpawnsMundoAbierto {

    /**
     * Clase interna que representa una tarea de spawn de enemigos.
     * Implementa Runnable para ser ejecutada periódicamente por el scheduler.
     */
    static class SpawnTarea implements Runnable {

        // Arrays con las zonas y enemigos disponibles para spawns aleatorios
        private final String[] zonas = {
                "Bosque Maldito",
                "Ruinas Antiguas",
                "Pantano Radiactivo",
                "Ciudad Cibernética",
                "Templo Prohibido"
        };

        private final String[] enemigos = {
                "Slime Mutante",
                "Esqueleto Guerrero",
                "Mecha-Dragón",
                "Bandido del Desierto",
                "Lich Supremo"
        };

        /**
         * Método que ejecuta la tarea de spawn.
         * Selecciona aleatoriamente una zona y un enemigo, y simula el proceso de
         * spawn.
         */
        @Override
        public void run() {
            String hilo = Thread.currentThread().getName();
            // Selecciona una zona y un enemigo aleatoriamente
            String zona = zonas[(int) (Math.random() * zonas.length)];
            String enemigo = enemigos[(int) (Math.random() * enemigos.length)];
            System.out.println("[" + LocalTime.now() + "][" + hilo + "] Spawn de " +
                    enemigo + " en " + zona);
            // Simula un trabajo que toma tiempo para probar el comportamiento cuando dura
            // más que el período
            try {
                Thread.sleep(2500); // 2.5 segundos, mayor que el período de 2 segundos
            } catch (InterruptedException e) {
                System.out.println("[" + hilo + "] Tarea de spawn interrumpida");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        System.out.println("Iniciando spawns programados cada 2 segundos...");
        // Ejecutar la tarea cada 2 segundos, sin retraso inicial
        scheduler.scheduleAtFixedRate(new SpawnTarea(), 0, 2, TimeUnit.SECONDS);

        // Dejamos que el mundo “viva” durante 12 segundos
        Thread.sleep(12000);

        System.out.println("Deteniendo spawns...");
        scheduler.shutdown();
        if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("Forzando parada de spawns.");
            scheduler.shutdownNow();
        }
        System.out.println("Servidor de mundo abierto detenido.");
    }
}
