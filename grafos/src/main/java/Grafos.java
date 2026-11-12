/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

//Trabajo: José Luis Bustamante Madrid

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class GrafoArrays {

    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;

    // Arcos de DFS y BFS
    private ArrayList<String> arcosDFS;
    private ArrayList<String> arcosBFS;

    public GrafoArrays(String origen) {
        creaTablaVertices(origen);
        matrizAdyacencia = new int[numVertices][numVertices];
        populateMatrizAdyacencia(origen);

        arcosDFS = new ArrayList<>();
        arcosBFS = new ArrayList<>();
    }

    private void creaTablaVertices(String origen) {
        String[] lineas = origen.split("\\R");
        vertices = new String[lineas.length * 2];
        numVertices = 0;

        for (String linea : lineas) {
            String arista = linea.trim();

            if (!arista.contains("--")) {
                continue;
            }

            int etiqueta = arista.indexOf('[');

            if (etiqueta >= 0) {
                arista = arista.substring(0, etiqueta);
            }

            String[] extremos = arista.split("--", 2);

            if (extremos.length == 2) {
                agregarVertice(extremos[0].trim());
                agregarVertice(extremos[1].trim());
            }
        }
    }

    private void populateMatrizAdyacencia(String origen) {
        String[] lineas = origen.split("\\R");

        for (String linea : lineas) {
            String arista = linea.trim();

            if (!arista.contains("--")) {
                continue;
            }

            int peso = 1;
            int etiqueta = arista.indexOf('[');

            if (etiqueta >= 0) {
                int cierre = arista.indexOf(']', etiqueta);

                if (cierre >= 0) {
                    String pesoStr = arista.substring(
                            etiqueta + 1, cierre);

                    // Obtener el numero del peso
                    pesoStr = pesoStr.replaceAll("[^0-9]", "");

                    if (!pesoStr.isEmpty()) {
                        peso = Integer.parseInt(pesoStr);
                    }
                }

                arista = arista.substring(0, etiqueta);
            }

            String[] extremos = arista.split("--", 2);

            if (extremos.length == 2) {
                agregarArista(extremos[0].trim(),
                        extremos[1].trim(), peso);
            }
        }
    }

    private void agregarVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return;
            }
        }

        vertices[numVertices] = vertice;
        numVertices++;
    }

    private int obtenerIndiceVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    private void agregarArista(String vertice1,
            String vertice2, int peso) {

        int indice1 = obtenerIndiceVertice(vertice1);
        int indice2 = obtenerIndiceVertice(vertice2);

        if (indice1 >= 0 && indice2 >= 0) {
            matrizAdyacencia[indice1][indice2] = peso;
            matrizAdyacencia[indice2][indice1] = peso;
        }
    }

    public void display() {
        System.out.println("Vertices del grafo:");
        displayVertices();
        displayMatrizAdyacencia();
    }

    private void displayVertices() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }
    }

    private void displayMatrizAdyacencia() {
        System.out.println("\nMatriz de Adyacencia:");

        System.out.print("       ");

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%5s",
                    vertices[i].substring(0,
                            Math.min(3, vertices[i].length())));
        }

        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-7s",
                    vertices[i].substring(0,
                            Math.min(6, vertices[i].length())));

            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%5d", matrizAdyacencia[i][j]);
            }

            System.out.println();
        }
    }

    // Recorrido DFS
    public void DFS(String verticeInicial, String verticeFinal) {

        arcosDFS.clear();

        boolean[] visitado = new boolean[numVertices];

        int inicio = obtenerIndiceVertice(verticeInicial);
        int fin = obtenerIndiceVertice(verticeFinal);

        if (inicio == -1 || fin == -1) {
            System.out.println("Uno o ambos vertices no existen.");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int[] padre = new int[numVertices];

        stack.push(inicio);
        visitado[inicio] = true;
        padre[inicio] = -1;

        System.out.println("\nRecorrido DFS:");

        while (!stack.isEmpty()) {

            int actual = stack.pop();

            System.out.print(vertices[actual] + " ");

            if (padre[actual] != -1) {
                arcosDFS.add(vertices[padre[actual]]
                        + " -- " + vertices[actual]);
            }

            if (actual == fin) {
                System.out.println("\nVertice final alcanzado.");
                return;
            }

            for (int i = numVertices - 1; i >= 0; i--) {
                if (matrizAdyacencia[actual][i] != 0
                        && !visitado[i]) {

                    visitado[i] = true;
                    padre[i] = actual;
                    stack.push(i);
                }
            }
        }

        System.out.println("\nNo se encontro el vertice final.");
    }

    // Recorrido BFS
    public void BFS(String verticeInicial, String verticeFinal) {

        arcosBFS.clear();

        boolean[] visitado = new boolean[numVertices];

        int inicio = obtenerIndiceVertice(verticeInicial);
        int fin = obtenerIndiceVertice(verticeFinal);

        if (inicio == -1 || fin == -1) {
            System.out.println("Uno o ambos vertices no existen.");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] padre = new int[numVertices];

        queue.add(inicio);
        visitado[inicio] = true;
        padre[inicio] = -1;

        System.out.println("\nRecorrido BFS:");

        while (!queue.isEmpty()) {

            int actual = queue.poll();

            System.out.print(vertices[actual] + " ");

            if (padre[actual] != -1) {
                arcosBFS.add(vertices[padre[actual]]
                        + " -- " + vertices[actual]);
            }

            if (actual == fin) {
                System.out.println("\nVertice final alcanzado.");
                return;
            }

            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[actual][i] != 0
                        && !visitado[i]) {

                    visitado[i] = true;
                    padre[i] = actual;
                    queue.add(i);
                }
            }
        }

        System.out.println("\nNo se encontro el vertice final.");
    }

    // Mostrar arcos y comparar recorridos
    public void despliegaArcos() {

        int totalArcos = 0;

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (matrizAdyacencia[i][j] != 0) {
                    totalArcos++;
                }
            }
        }

        System.out.println("\n--- RESULTADOS ---");

        System.out.println("Total de vertices: " + numVertices);
        System.out.println("Total de arcos: " + totalArcos);

        System.out.println("\nArcos DFS:");
        for (String arco : arcosDFS) {
            System.out.println(arco);
        }

        System.out.println("Arcos ocupados DFS: "
                + arcosDFS.size());

        System.out.println("\nArcos BFS:");
        for (String arco : arcosBFS) {
            System.out.println(arco);
        }

        System.out.println("Arcos ocupados BFS: "
                + arcosBFS.size());

        System.out.println("\nTotal de arcos ocupados: "
                + (arcosDFS.size() + arcosBFS.size()));

        if (arcosDFS.size() < arcosBFS.size()) {
            System.out.println("DFS utiliza menos arcos.");
        } else if (arcosBFS.size() < arcosDFS.size()) {
            System.out.println("BFS utiliza menos arcos.");
        } else {
            System.out.println("Ambos utilizan la misma cantidad.");
        }
    }
}

public class Grafos {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("Archivos disponibles:");
        System.out.println("1. grafo_salon.txt");
        System.out.println("2. grafo_salon2.txt");
        System.out.println("3. grafo_salon3.txt");

        System.out.print("Selecciona una opcion: ");
        int opcion = entrada.nextInt();
        entrada.nextLine();

        String archivo;

        switch (opcion) {
            case 1:
                archivo = "grafo_salon.txt";
                break;
            case 2:
                archivo = "grafo_salon2.txt";
                break;
            case 3:
                archivo = "grafo_salon3.txt";
                break;
            default:
                System.out.println("Opcion no valida.");
                return;
        }

        String texto = cargaArchivo("C:\\Users\\pinin\\OneDrive\\Documentos\\Fundamentos3\\grafos\\src\\main\\java\\com\\mycompany\\grafos\\" + archivo);

        if (texto.isEmpty()) {
            return;
        }

        GrafoArrays grafo = new GrafoArrays(texto);

        grafo.display();

        System.out.print("\nVertice inicial: ");
        String inicial = entrada.nextLine();

        System.out.print("Vertice final: ");
        String fin = entrada.nextLine();

        grafo.DFS(inicial, fin);
        grafo.BFS(inicial, fin);
        grafo.despliegaArcos();
    }

    public static String cargaArchivo(String ruta) {

        String contenido = "";

        try {
            File archivo = new File(ruta);
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                contenido += scanner.nextLine() + "\n";
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        }

        return contenido;
    }
}