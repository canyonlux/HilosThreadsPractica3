package org.example;
public class IncrementCounterThreads {
    private static int contador = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new Incrementor(i)); // Creamos un nuevo hilo y le pasamos un objeto Incrementor con un ID único.
            threads[i].start(); // Iniciamos el hilo.
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join(); // Esperamos a que el hilo termine su ejecución.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El valor final del contador es: " + contador);
    }

    static class Incrementor implements Runnable {
        private final int threadId;

        Incrementor(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            for (int i = 0; i < 500; i++) {
                synchronized (IncrementCounterThreads.class) {
                    contador++; // Incrementamos el contador dentro de una sección crítica sincronizada.
                }
            }
            System.out.println("Hilo " + threadId + " ha terminado.");
        }
    }
}
// el valor siempre sera 2000 porque se esta sincronizando el acceso al contador y solo un hilo puede acceder a el a la vez.