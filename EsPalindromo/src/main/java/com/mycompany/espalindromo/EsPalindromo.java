/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.espalindromo;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author Jose Luis Bustamante Madrid
 */
public class EsPalindromo {

    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "ama";
        boolean esPalindromo = esPalindromo(cadena);
        System.out.println("Cadena: " + cadena);
        System.out.println("¿Es palíndromo? " + esPalindromo);
        
    }
    public static boolean esPalindromo(String cadena) {
        Deque<Character> pila = newArrayDeque<>();
        for(char c : cadena.toCharArray()) {
            pila.push(c);
        }
        char[] cadenaInvertidaChar = new char [cadena.length()];
        while(!pila.isEmpty()) {
            cadenaInvertidaChar[cadena.length() - pila.size()] = pila.pop();
        }
        String cadenaInvertidaStr = new String(cadenaInvertidaChar);
        System.out.println("Cadena invertida: " + cadenaInvertidaStr);
        
        return cadena.equals(cadenaInvertidaStr);
    }
    
}
