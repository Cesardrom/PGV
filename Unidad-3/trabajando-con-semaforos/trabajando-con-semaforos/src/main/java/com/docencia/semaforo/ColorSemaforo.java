package com.docencia.semaforo;

public class ColorSemaforo implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
        String[] colors = { "ROJO", "VERDE", "ÁMBAR" };
        int[] times = { 3000, 3000, 1000 };
        int index = 0;
        while (running) {
            System.out.println(colors[index]);
            try {
                Thread.sleep(times[index]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            index = (index + 1) % 3;
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        ColorSemaforo semaforo = new ColorSemaforo();
        Thread thread = new Thread(semaforo);
        thread.start();
        Thread.sleep(20000);
        semaforo.stop();
        thread.interrupt();
        thread.join();
    }
}
