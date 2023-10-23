package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TiendaComestibles {

    public static void main(String[] args) {
        BlockingQueue<String> buffer = new ArrayBlockingQueue<>(5); // Tamaño del buffer

        // Hilo productor
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String producto = "Hamburguesa doble con queso " + i;
                    buffer.put(producto); // Poner producto en el buffer
                    System.out.println("Productor produce: " + producto);
                    Thread.sleep(1000); // Simular tiempo de producción
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Hilo consumidor
        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    String producto = buffer.take(); // Tomar producto del buffer
                    System.out.println("Consumidor consume: " + producto);
                    Thread.sleep(1500); // Simular tiempo de consumo
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();
    }
}
