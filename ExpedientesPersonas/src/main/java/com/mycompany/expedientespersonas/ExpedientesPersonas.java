/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.expedientespersonas;

import java.util.ArrayList;

/**
 *
 * @author Jose Luis Bustamante Madrid
 */
public class ExpedientesPersonas {
    
    public static Personas buscarPersonaEnLista(ArrayList<Personas> personas, String nombre) {
        for (Personas persona : personas) {
            if (persona.getNombre().equalsIgnoreCase(nombre)) {
                return persona;
            }
        }
        return null; // Retorna null si no se encuentra la persona
    }

    public static void buscarVariasPersonasEnLista(ArrayList<Personas> personas, String[] nombres) {
        // Inicia un timer para medir el tiempo de búsqueda
        long startTime = System.nanoTime();
        for (String nombre : nombres) {
            Personas personaEncontrada = buscarPersonaEnLista(personas, nombre);
            if (personaEncontrada != null) {
                personaEncontrada.mostrarDatos();
            } else {
                System.out.println("No se encontró a la persona con nombre: " + nombre);
            }
        }
        // Finaliza el timer y muestra el tiempo de búsqueda
        long endTime = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (endTime - startTime) / 1000000.0 + " ms");
    }

    public class Personas {
            public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Expediente: " + expediente);
        System.out.println("Edad: " + edad);
    }

        public Personas() {
        }
    }
 
}
