/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.expedientespersonas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pinin
 */
class Personas {
    private String nombre;
    private int expediente;
    private int edad;

    public Personas(String nombre, int expediente, int edad) {
        this.nombre = nombre;
        this.expediente = expediente;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public int getExpediente() {
        return expediente;
    }

    public int getEdad() {
        return edad;
    }
}

public class ExpedientesPersonas {
    public static ArrayList<Personas> cargaArchivoPersonas(String nombreArchivo) {
        ArrayList<Personas> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    int expediente = Integer.parseInt(partes[1].trim());
                    int edad = Integer.parseInt(partes[2].trim());
                    personas.add(new Personas(nombre, expediente, edad));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return personas;
    }
}