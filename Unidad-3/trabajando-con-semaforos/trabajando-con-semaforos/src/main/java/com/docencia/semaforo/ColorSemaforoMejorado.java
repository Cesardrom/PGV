package com.docencia.semaforo;

import java.util.concurrent.Semaphore;

public class ColorSemaforoMejorado implements Runnable {
    private volatile boolean running = true;
    private Semaphore semaforo = new Semaphore(1);

    @Override
    public void run() {
        String[] colors = { "ROJO", "VERDE", "ÁMBAR" };
        int[] times = { 3000, 3000, 1000 };
        int index = 0;
        while (running) {
            try {
                semaforo.acquire();
                System.out.println(colors[index]);
                semaforo.release();
                try {
                    Thread.sleep(times[index]);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                index = (index + 1) % 3;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ColorSemaforoMejorado semaforo = new ColorSemaforoMejorado();
        Thread thread = new Thread(semaforo);
        thread.start();
        Thread.sleep(20000);
        semaforo.stop();
        thread.interrupt();
        thread.join();
    }
}
