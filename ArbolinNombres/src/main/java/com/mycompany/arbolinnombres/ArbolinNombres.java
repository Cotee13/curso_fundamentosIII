/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arbolinnombres;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José Luis Bustamante Madrid
 */
public class ArbolinNombres {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Nodo {
    int valor;
    Nodo izquierda;
    Nodo derecha;
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}
