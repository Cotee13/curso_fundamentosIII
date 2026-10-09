/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recursividad;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pinin
 */
public class Recursividad {
    static Map<Integer, Long> memo = new HashMap<>();

    public static void main(String[] args) {
        //tiempo inicial
        int[] arr = {7, 15, 30, 45, 60};
        long startTime = System.nanoTime();
        System.out.println("Fibonacci de 7: " + fibonacci(7));
        //System.out.println("Factorial de 32: " + factorial(32));
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecuión: " + (endTime - startTime)/ 100000.0 + "ms");
    }
    public static long fib(int n) {
        if(n <= 1) return n;
        Long guardado = memo.get(n);
        if(guardado != null) return guardado; 
        long resultado = fib(n-1) + fib(n-2);
        memo.put(n, resultado);
        return resultado;
    }
    
    public static int factorial(int n) {
        if(n ==0) {
            return 1;
        } else {
            return n * factorial(n-1); //n = 5
        }
    }
    
    public static int fibonacci(int n) {
        if(n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }
}
