package com.MAsidah;

import java.util.Scanner;

public class Main {


    public static boolean isBinaryNumber(String bin) {
        boolean isBinary = true;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) != '0' && bin.charAt(i) != '1')
                isBinary = false;
        }
        return isBinary;
    }

    public static String getStringNumber(Scanner in) {
        String bin;
        boolean esBin = true;
        do {
            bin = in.next();
            esBin = isBinaryNumber(bin);

            if (!esBin) System.out.println("No es un numero binario, introduce de nuevo.");
        } while (!esBin);
        return bin;
    }

    public static boolean[] getBinaryNumber(Scanner in, String num) {
        boolean[] binary;

        int size = num.length();
        binary = new boolean[size];

        for (int i = 0; i < size; i++) {
            binary[i] = num.charAt(i) == '1';
        }
        binary = reverseNumber(binary);
        return binary;
    }

    public static boolean[] reverseNumber(boolean[] number) {
        boolean[] invNumber = new boolean[number.length];
        int numSize = number.length;
        for (int i = 0, j = numSize - 1; i < numSize && j >= 0; j--, i++) {
            invNumber[i] = number[j];
        }
        return invNumber;
    }

    public static void printBinaryNumber(boolean[] number) {
        for (int i = 0; i < number.length; i++) {
            if (number[i])
                System.out.print('1');
            else
                System.out.print('0');
        }
        System.out.println();
    }

    public static int binaryToDecimal(boolean[] number) {
        int res = 0;
        int exp = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            exp = number.length - (i + 1);
            if (number[i])
                res += Math.pow(2, exp);
        }
        return res;
    }

    /**
     * Devuelve el contenido de small en un array de la longitud de big
     *
     * @param num
     * @param size
     * @return
     */
    public static boolean[] resize(boolean[] num, int size) {

        boolean[] aux = new boolean[size];

        for (int i = num.length - 1, j = size - 1; i >= 0; i--, j--) {
            aux[j] = num[i];
        }
        return aux;
    }

    public static boolean[] sumBinaryNumbers(boolean[] a, boolean[] b) {
        int sizeA = a.length, sizeB = b.length;
        int sizeRes = (sizeA >= sizeB) ? sizeA + 1 : sizeB + 1;

        boolean carry = false;
        boolean[] res = new boolean[sizeRes];

        //Iguala el menor vector al tamaño del mayor
        if (sizeA > sizeB)
            b = resize(b, sizeA);
        else if (sizeB > sizeA)
            a = resize(a, sizeB);

        for (int i = sizeRes - 2; i >= 0; i--) {
            /*if (a[i] != b[i] || (!a[i] && !b[i])) {
                if (!carry) {
                    res[i] = a[i] || b[i];
                }
            }*/

            // 1 + 1
            if (a[i] && b[i]) {
                if (!carry) {
                    res[i] = false;
                    carry = true;
                } else {
                    res[i] = true;
                    carry = true;
                }
            }
            //0 + 0
            else if (!a[i] && !b[i]) {
                res[i] = (carry) ? true : false;
                carry = false;

                // 1 + 0 || 0 + 1
            } else if ((a[i] && !b[i]) || (!a[i] && b[i])) {
                if (!carry)
                    res[i] = true;
                else {
                    res[i] = false;
                    carry = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int size = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Number1:");
        String s = getStringNumber(in);
        boolean[] number1 = new boolean[s.length()];
        number1 = getBinaryNumber(in, s);

        printBinaryNumber(number1);


        System.out.println("Number2:");
        s = getStringNumber(in);
        boolean[] number2 = new boolean[s.length()];
        number2 = getBinaryNumber(in, s);

        printBinaryNumber(number2);

        sumBinaryNumbers(number1, number2);
        int res = binaryToDecimal(number1);
        System.out.println(res);

    }

}
