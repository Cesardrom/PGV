package com.docencia.semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Estudiante extends Thread {
    private String nombre;
    private Semaphore semaforo;

    public Estudiante(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println("El estudiante " + nombre + " ha comenzado a utilizar el equipo");
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5001));
            System.out.println("El estudiante " + nombre + " ha finalizado con el equipo");
            semaforo.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaforo = new Semaphore(4);
        Estudiante[] estudiantes = new Estudiante[6];
        for (int i = 0; i < 6; i++) {
            estudiantes[i] = new Estudiante(String.valueOf(i + 1), semaforo);
            estudiantes[i].start();
        }
        for (Estudiante estudiante : estudiantes) {
            estudiante.join();
        }
    }
}
