/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hanoitowers;
import java.util.Scanner;
import java.util.Stack;

//Author: José Luis Bustamante Madrid
//Date: 09-15-2026

public class HanoiTowers {
    static Scanner sc = new Scanner(System.in);
    static int  numDiscos = 3;
    static Stack<Integer>[] torres = new Stack[3];

    public static void main(String[] args) {
       menuPrincipal();
    }
    static int leerEntero() {
        while(!sc.hasNextInt()) {
            System.out.print("Ingresa un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }
    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== TORRES DE HANOI =====");
            System.out.println("1. Elegir numero de discos (3-8)");
            System.out.println("2. Jugar manualmente");
            System.out.println("3. Mostrar solucion automatica");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    elegirNumeroDiscos();
                    break;
                case 2:
                    jugarManual();
                    break;
                case 3:
                    mostrarSolucion();
                    break;
                case 4:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion !=4);
    }
    static void inicializaTorres() {
        for(int i = 0; i < 3; i++){
            torres[i] = new Stack<>();
        }
        for(int i = numDiscos; i >= 1; i--) {
            torres[0].push(i);
        }
    }
    
    static void despliegaTorre(int torre) {
        System.out.print("Torre " + (char)('A' + torre) + ": ");
        for(int disco : torres[torre]) {
            System.out.print(disco + "");
        }
        System.out.println();
    }
    static void despliegaTorres() {
        for(int i = 0; i < 3; i++) {
            despliegaTorre(i);
        }
    }
    public static void elegirNumeroDiscos() {
        int n;
        do {
            System.out.print("Ingresa el numero de discos (3-8): ");
            n = leerEntero();
            if (n < 3 || n > 8) {
                System.out.println("Numero invalido. Debe estar entre 3 y 8.");
            }
        } while (n < 3 || n > 8);
        numDiscos = n;
    }
    public static void jugarManual() {
        inicializaTorres();
        despliegaTorres();
        while(numDiscos>torres[2].size()) {
            //logica para jugar manualmente
            despliegaTorres();
            String origen = eligeTorre("Elige la torre de origen (A, B, C): ");
            String destino = eligeTorre("Elige la torre de destino (A, B, C): ");
            int torreOrigen = origen.charAt(0) - 'A';
            int torreDestino = destino.charAt(0) - 'A';
            if(torres[torreOrigen].isEmpty()){
                System.out.println("La torre de origen esta vacia, intenta de nuevo.");
                continue;
            }
            if(torreOrigen == torreDestino) {
                System.out.println("La torre de origen y destino son las mismas, intenta de nuevo.");
                continue;
            }
            moverDisco(torreOrigen, torreDestino);
        }
        System.out.println("¡Felicidades! Has completado el juego.");
    }
    public static boolean moverDisco(int origen, int destino) {
        if(torres[origen].isEmpty()) {
            System.out.println("No hay discos en la torre de origen.");
            return false;
        }
        if(!torres[destino].isEmpty() && torres[destino].peek() < torres[origen].peek()) {
            System.out.println("Movimiento invalido, no puedes colocar un disco mas grande sobre uno mas pequeno");
            return false;
        }
        int disco = torres[origen].pop();
        torres[destino].push(disco);
        System.out.println("Moviste el disco " + disco + " de la torre " + (char)('A' + origen) + " a la torre " + (char)('A' + destino) + ".");
        return true;
    }
    public static String eligeTorre(String mensaje) {
        String torre;
        do {
            System.out.print(mensaje);
            torre = sc.next().toUpperCase();
            if(!torre.equals("A") && !torre.equals("B") && !torre.equals("C")) {
                System.out.println("Torre invalida. Debe ser A, B o C.");
            }
        }while (!torre.equals("A") && !torre.equals("B") && !torre.equals("C"));
        return torre;
    }
    public static void mostrarSolucion() {
        inicializaTorres();
        System.out.println("\n--- ESTADO INICIAL ---");
        despliegaTorres();
        resolverHanoi(numDiscos, 0, 1, 2);
        
        System.out.println("¡Solución automática terminada!");
    }

    public static void resolverHanoi(int n, int origen, int auxiliar, int destino) {
        if (n == 1) {
            moverDisco(origen, destino);
            despliegaTorres();
            return;
        }
        
        // Mover n-1 discos de origen a auxiliar usando destino como apoyo
        resolverHanoi(n - 1, origen, destino, auxiliar);
        
        // Mover el disco restante de origen a destino
        moverDisco(origen, destino);
        despliegaTorres();
        
        // Mover los n-1 discos de auxiliar a destino usando origen como apoyo
        resolverHanoi(n - 1, auxiliar, origen, destino);
    }
}