/*
Autor: Mohamed Asidah Bchiri
 */
package com.MAsidah;

import java.util.Random;

public class Main {

    /**
     * Inicializa de forma aleatoria a los participantes en la primera fila
     * @param campo
     */
    public static void iniciarCampo(int[][] campo) {
        int participantes = 456;
        Random r = new Random();
        for (int j = 0; j < campo[0].length && participantes > 0; j++) {


            campo[0][j] += r.nextInt(participantes);
            participantes -= campo[0][j];
        }
        if (participantes > 0)
            campo[0][r.nextInt(campo.length)] += participantes;

    }

    /**
     * Comprueba si una fila esta vacía
     * @param campo
     * @param fila
     * @return
     */
    public static boolean filaVacia(int[][] campo, int fila) {
        boolean vacio = true;
        for (int i = 0; i < campo.length && vacio; i++) {
            vacio = campo[fila][i] == 0;
        }
        return vacio;
    }


    /**
     * Avanza una fila a los participantes no jugadores.
     * @param campo
     * @param fila
     */
    public static void avanzarNPCS(int[][] campo, int fila) {
        for (int i = 0; i < campo.length; i++) {
            campo[fila + 1][i] = campo[fila][i];
            campo[fila][i] = 0;
        }
    }

    /**
     * Avanza en linea recta siempre que sea posible al jugador
     * @param jugador
     * @param campo
     * @return
     */
    public static int[] avanzarRecto(int[] jugador, int[][] campo) {
        int[] nuevaPos = new int[2];
        if (!(nuevaPos[0] + 1 >= campo.length))
            nuevaPos[0] = jugador[0] + 1;
        else
            nuevaPos[0] = jugador[0];
        nuevaPos[1] = jugador[1];

        return nuevaPos;
    }

    /**
     * Avanza en diagonal derecha siempre que sea posible al jugador
     * @param jugador
     * @return
     */
    public static int[] avanzarDiagonalDerecha(int[] jugador, int[][] campo) {
        int[] nuevaPos = new int[2];
        nuevaPos[0] = jugador[0];
        nuevaPos[1] = jugador[1];
        //comprueba si la diagonal derecha es posible
        if (nuevaPos[1] != 0) {
            nuevaPos[1]--;
            nuevaPos[0]++;
        } else //si no es posible avanza recto
            avanzarRecto(jugador, campo);
        return nuevaPos;
    }
    /**
     * Avanza en diagonal izquierda siempre que sea posible al jugador
     * @param jugador
     * @return
     */
    public static int[] avanzarDiagonalIzquierda(int[] jugador, int[][] campo) {
        int[] nuevaPos = new int[2];
        nuevaPos[0] = jugador[0];
        nuevaPos[1] = jugador[1];
        //comprueba si la diagonal izquierda es posible
        if (nuevaPos[1] < campo.length - 1) {
            nuevaPos[1]++;
            nuevaPos[0]++;
        } else
            avanzarRecto(jugador, campo);
        return nuevaPos;
    }

    /**
     * Comprueba si en la siguiente casilla hay mas de dos jugadores
     * @param campo
     * @param casilla
     * @return
     */
    public static boolean comprobarCasillaOcupada(int[][] campo, int[] casilla) {
        return campo[casilla[0]][casilla[1]] > 2;
    }

    /**
     * Mueve al jugador de acuerdo a las condiciones puestas
     * @param jugador
     * @param campo
     */
    public static void moverJugador(int[] jugador, int[][] campo) {
        //avance del jugador
        Random r = new Random();
        int[] nuevaPos = new int[2];
        //50% de posibilidades de avanzar en linea recta
        if (r.nextInt(100) < 50) {
            nuevaPos = avanzarRecto(jugador, campo);
            if (!comprobarCasillaOcupada(campo, nuevaPos))
                jugador = nuevaPos;
        } else {
            //50% posibiliades de ir diagonal derecha
            if (r.nextInt(100) < 50) {
                nuevaPos = avanzarDiagonalDerecha(jugador,campo);
                if (!comprobarCasillaOcupada(campo, nuevaPos))
                    jugador = nuevaPos;
            } else {
                nuevaPos = avanzarDiagonalIzquierda(jugador, campo);
                if (!comprobarCasillaOcupada(campo, nuevaPos))
                    jugador = nuevaPos;
            }
        }
    }

    /**
     * Decide si el jugador muere
     * @return
     */
    public static boolean jugadorMuere() {
        boolean muere = false;
        Random r = new Random();
        if (r.nextInt(100) < 50) { //La muñeca nos mira
            if (r.nextInt(100) < 5)
                muere = true;
            else if (r.nextInt(100) < 5)
                muere = true;
            else if (r.nextInt(100) < 3)
                muere = true;
        }
        return muere;
    }

    /**
     * Mueve a todos los participantes del juego
     * @param campo
     * @param jugador
     */
    public static void luzVerde(int[][] campo, int[] jugador) {
        //avance de los npc
        for (int i = 0; i < campo.length; i++) {
            if (!filaVacia(campo, i)) {
                avanzarNPCS(campo, i);
            }
        }
        //movimiento del jugador
        moverJugador(jugador, campo);

    }

    /**
     * Calcula cuantos participantes mueren
     * @param participantes
     * @return
     */
    public static int participantesMuertos(int participantes) {
        int muertes = 0;
        Random r = new Random();
        //calcula cuantos mueren en cada casilla
        for (int i = 0; i < participantes; i++) {
            if (r.nextInt(100) < 5)
                muertes++;
            else if (r.nextInt(100) < 5)
                muertes++;
            else if (r.nextInt(100) < 3)
                muertes++;
        }
        return muertes;
    }

    /**
     * La muñeca comprueba quien se mueve
     * @param campo
     * @param jugador
     * @param participantes
     * @return
     */
    public static boolean luzRoja(int[][] campo, int[] jugador, int participantes) {
        boolean jugadorMuerto = false;
        //la muñeca comprueba casilla a casilla
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {
                if (campo[i][j] > 0) {
                    campo[i][j] -= participantesMuertos(campo[i][j]);
                }
                if (i == jugador[0] && j == jugador[1])
                    jugadorMuerto = jugadorMuere();
            }
        }
        return jugadorMuerto;
    }

    public static void mostrarTablero(int[][] campo, int[] jugador, int participantes) {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {
                System.out.print("[ " + campo[i][j]);
                if (jugador[0] == i && jugador[1] == j)
                    System.out.print(" X]");
                System.out.print("]");
                System.out.println();

            }
        }
        System.out.println("Quedan " + participantes + " participantes vivos.");
    }

    public static void gameLoop(int[][] campo, int[] jugador, int participantes) {
        int tiempo = 5000;
        boolean jugadorVivo = true;
        while (tiempo > 0 && jugadorVivo) {
            System.out.println("LUZ VERDE!!");
            luzVerde(campo, jugador);

            System.out.println("LUZ ROJA!!");
            jugadorVivo = luzRoja(campo, jugador, participantes);

            mostrarTablero(campo, jugador, participantes);
            tiempo -= 200;
        }
    }

    public static void main(String[] args) {
        int tam = 10;
        int participantes = 456;
        int[][] campo = new int[tam][tam];
        int[] jugador = new int[2];
        gameLoop(campo, jugador, participantes);

    }
}
