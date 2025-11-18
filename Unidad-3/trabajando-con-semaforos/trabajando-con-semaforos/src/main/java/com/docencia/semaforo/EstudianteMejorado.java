package com.docencia.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class EstudianteMejorado extends Thread {
    private String nombre;
    private Semaphore semaforo;

    public EstudianteMejorado(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            int equipo = semaforo.availablePermits() + 1;
            System.out.println("El estudiante " + nombre + " ha comenzado a utilizar el equipo " + equipo);
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001));
            System.out.println("El estudiante " + nombre + " ha finalizado con el equipo " + equipo);
            semaforo.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaforo = new Semaphore(4);
        EstudianteMejorado[] estudiantes = new EstudianteMejorado[6];
        for (int i = 0; i < 6; i++) {
            estudiantes[i] = new EstudianteMejorado(String.valueOf(i + 1), semaforo);
            estudiantes[i].start();
        }
        for (EstudianteMejorado estudiante : estudiantes) {
            estudiante.join();
        }
    }
}
