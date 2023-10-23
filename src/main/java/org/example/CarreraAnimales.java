package org.example;

import java.util.Random;

public class CarreraAnimales {
    private static final int DISTANCIA_CARRERA = 1000;
    private static final int NUMERO_PASOS = 1000;
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Creamos hilos para cada animal con su velocidad relativa
        Thread tortuga = new Thread(new Animal("Tortuga", 2));
        Thread liebre = new Thread(new Animal("Liebre", 9));
        Thread caballo = new Thread(new Animal("Caballo", 7));
        Thread perro = new Thread(new Animal("Perro", 5));

        // Iniciamos los hilos
        tortuga.start();
        liebre.start();
        caballo.start();
        perro.start();
    }

    static class Animal implements Runnable {
        private final String nombre;
        private final int velocidad; // Velocidad relativa del animal
        private int posicion = 0;

        Animal(String nombre, int velocidad) {
            this.nombre = nombre;
            this.velocidad = velocidad;
        }

        @Override
        public void run() {
            System.out.println(nombre + " ha comenzado la carrera.");
            for (int i = 0; i < NUMERO_PASOS; i++) {
                if (random.nextDouble() < 0.05) {
                    // El animal resbala y retrocede 2 posiciones (5% de probabilidad)
                    posicion = Math.max(0, posicion - 2);
                } else {
                    posicion += velocidad; // El animal avanza
                }

                if (posicion >= DISTANCIA_CARRERA) {
                    System.out.println(nombre + " ha terminado la carrera!");
                    break;
                }
            }
        }
    }
}
