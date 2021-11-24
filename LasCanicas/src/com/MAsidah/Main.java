/*
Autor: Mohamed Asidah Bchiri
 */
package com.MAsidah;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {


    /**
     * Recoge las canicas que apuesta el jugador
     *
     * @return
     */
    public static int apostarCanicas() {
        Scanner in = new Scanner(System.in);
        int apuesta = 0;

            try {
                apuesta = in.nextInt();
            } catch (InputMismatchException e) { //evita que el jugador introduza un no-entero
                System.out.println("Introduce un numero entero por favor.");
                in.next();
            }



        return apuesta;
    }

    /**
     * Simula la apuesta del oponente teniendo como maximo su bolsa
     *
     * @param bolsa
     * @return
     */
    public static int simularApuesta(int bolsa) {
        Random random = new Random();
        return random.nextInt(bolsa) + 1;
    }

    /**
     * Recoge las canicas que el jugador cree que ha apostado el jugador
     *
     * @return
     */
    public static String adivinarCanicas() {
        Scanner in = new Scanner(System.in);
        String apuesta;
        do {
            System.out.println("Apuesta: ¿Par o impar?");
            apuesta = in.next();
            apuesta = apuesta.toLowerCase();
        } while (!apuesta.equals("par") && !apuesta.equals("impar"));

        return apuesta;
    }

    /**
     * Simula si el oponente apuesta par o impar con un 50% de probabilidad cada una
     *
     * @return
     */
    public static String simularApuestaOponente() {
        Random random = new Random();
        String apuesta;
        if (random.nextInt(100) < 50)
            apuesta = "par";
        else
            apuesta = "impar";
        return apuesta;
    }

    /**
     * Comprueba si la apuesta hecha coincide con la paridad de las canicas
     *
     * @param apuesta
     * @param canicas
     * @return
     */
    public static boolean acertarApuesta(String apuesta, int canicas) {
        boolean acierto;
        if (apuesta.equals("par") && esPar(canicas))
            acierto = true;
        else if (apuesta.equals("impar") && !esPar(canicas))
            acierto = true;
        else
            acierto = false;
        return acierto;
    }

    /**
     * Comprueba si las canicas son pares o impares
     *
     * @param apuesta
     * @return
     */
    public static boolean esPar(int apuesta) {
        return (apuesta % 2 == 0);
    }


    /**
     * Comprueba si la bolsa pasada esta vacia y por ello su propietario muere
     *
     * @param bolsa
     * @return
     */
    public static boolean checkDeath(int bolsa) {
        return bolsa <= 0;
    }

    /**
     * Ejecuta el juego de las canicas
     *
     * @param jugador
     * @param oponente
     */
    public static void juegoCanicas(int jugador, int oponente) {
        int apuestaJugador, apuestaOponente;
        boolean jugadorAcierta, oponenteAcierta;
        do {

            //el jugador dice cuantas canicas apuesta
            do {
                System.out.println("Introduce tu apuesta (minimo 1, maximo " + jugador + "):");
                apuestaJugador = apostarCanicas();
                if (apuestaJugador <= 0 || apuestaJugador > jugador)
                    System.out.println("Apuesta incorrecta. Prueba otra vez.");
            } while (apuestaJugador <= 0 || apuestaJugador > jugador);

            //simula las canicas que apuesta el oponente
            apuestaOponente = simularApuesta(oponente);

            //el jugador apuesta si el oponente ha puesto pares o impares
            jugadorAcierta = acertarApuesta(adivinarCanicas(), apuestaOponente);

            //el oponente simula su apuesta
            oponenteAcierta = acertarApuesta(simularApuestaOponente(), apuestaJugador);


            if (oponenteAcierta == jugadorAcierta) //Si hay un empate
                System.out.println("Ha habido un empate, nadie pierde canicas esta ronda.");

            else if (oponenteAcierta) { //si ha ganado el oponente
                System.out.println("Tu oponente ha ganado, pierdes " + apuestaJugador + " canicas.");
                oponente += apuestaJugador;
                jugador -= apuestaJugador;

            } else if (jugadorAcierta) { // si ha ganado el jugador
                System.out.println("Has ganado, tu oponente pierde " + apuestaOponente + " canicas.");
                oponente -= apuestaOponente;
                jugador += apuestaOponente;
            }

        } while (!checkDeath(jugador) && !checkDeath(oponente));
        if (checkDeath(jugador))
            System.out.println("Has perdido.");
        else
            System.out.println("Has ganado.");
    }

    public static void main(String[] args) {
        int nCanicas = 10;

        // inicializar las bolsas de canicas
        int jugador = nCanicas, oponente = nCanicas;
        juegoCanicas(jugador, oponente);
    }
}
