/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inviertecadenas;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvierteCadenas {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "Hola, mundo!";
        String cadenaInvertida = invertirCadena(cadena);
        System.out.println("Cadena original: " + cadena);
        System.out.println("Cadena invertida: " + cadenaInvertida);
    }
    public static String invertirCadena(String cadena) {
        Deque<String> pila = new ArrayDeque<>();
        String [] palabras = cadena.split(" ");
        for(String palabra : palabras) {
            pila.push(palabra);
        }
        StringBuilder palabraInvertida = new StringBuilder();
        while(!pila.isEmpty()) {
            palabraInvertida.append(pila.pop()).append(" ");
        }
        return palabraInvertida.toString().trim();
       
    }

}
