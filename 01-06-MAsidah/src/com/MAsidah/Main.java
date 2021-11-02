package com.MAsidah;

import java.util.Scanner;

public class Main {
    /**
     * Busca una secuencia en la cadena y devuelve si esta incluida o no
     * @param cadena
     * @param secuencia
     * @return
     */
    public static boolean estaIncluido(String cadena, String secuencia){
        boolean estaIncluida = false;
        int j = 0, i = 0;
        while(i < cadena.length() && !estaIncluida) {
            if(cadena.charAt(i) == secuencia.charAt(0)){
                estaIncluida = true;
                j = 0;
                while(estaIncluida && j<secuencia.length()) if (cadena.charAt(i) != secuencia.charAt(j)) {
                    estaIncluida = false;
                } else {
                    i++;
                    j++;
                }
            }else i++;

        }
        return estaIncluida;
    }


    public static void salida(boolean incluido){
        String mensaje = (incluido)? "La secuencia está incluida en la cadena.":"La secuencia no está incluida en la cadena.";
        System.out.println(mensaje);
    }
    public static void main(String[] args) {
        String cadena, secuencia;
        Scanner in = new Scanner(System.in);

        //datos de entrada
        System.out.println("Introduce una cadena:");
        cadena = in.next();
        System.out.println("Introduce una cadena:");
        secuencia = in.next();


        salida(estaIncluido(cadena, secuencia));

    }

}
