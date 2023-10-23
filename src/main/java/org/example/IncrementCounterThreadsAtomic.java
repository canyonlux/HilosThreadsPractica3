package org.example;
import java.util.concurrent.atomic.AtomicInteger;

public class IncrementCounterThreadsAtomic {
    private static AtomicInteger contador = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new Incrementor(i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Esperamos a que todos los hilos terminen.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El valor final del contador es: " + contador.get());
    }

    static class Incrementor implements Runnable {
        private final int threadId;

        Incrementor(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            for (int i = 0; i < 500; i++) {
                contador.getAndIncrement(); // Incrementamos el contador de forma atómica.
            }
            System.out.println("Hilo " + threadId + " ha terminado.");
        }
    }
}
//Con el uso de AtomicInteger, garantizamos que el acceso a la variable compartida sea seguro y que no haya condiciones de carrera, lo que proporciona un resultado correcto
