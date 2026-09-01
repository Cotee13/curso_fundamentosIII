/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebalinkedlist;

/**
 *
 * @author José Luis Bustamante Madrid
 */
class Student {
    String name;
    int score;
    Student next;
    public Student(String name, int score) {
        this.name = name;
        this.score= score;
        this.next = null;
    }
}
class LnkdLst {
    Student head;
    Student tail;
    int index = 0;
    public LnkdLst() // Constructor
    {
        this.head = null;
        this.tail = null;
    }
    //Agrega un nuevo estudiante al final de la lista
    public void add(String name, int score) {
        Student newStudent = new Student(name,score);
        if (head == null) {
            head = newStudent;
            tail = newStudent;
            this.index++;
            return;
        }
        tail.next = newStudent;
        tail = newStudent;
        this.index++;
    }
    public void print() {
        Student current = this.head;
        while (current !=null) {
            System.out.println(current.name +
                       " - " + current.score);
            current = current.next;
        }
    }
        public Student retrieve(String searchedName) {
        Student current = this.head;
        while (current != null) {
            if (current.name.equals(searchedName))
            { return current; }
            current = current.next; //traverse the list
        }
        return null; // not found
    }
    public void remove(int n){
        if (head == null) { return; } // Empty list
        if (n < 0) { return; } // negative index
        if (n >= this.index) { return; } // index out of bounds
        if (n == 0) { // remove head
            head = head.next;
            this.index--;
            return;
        }
        if (n == this.index - 1) { // remove tail
            Student current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            this.index--;
            return;
        }
        int index = 0;
        Student current = head; 
        while ( current !=null && index < (n-1)) {
            current = current.next;
            index++;
        }
        if (current==null || current.next == null) {
            return; // position out of range
        }
        current.next = current.next.next;

        this.index--;
    }
}
public class PruebaLinkedlist {
    public static void main(String[] args) {
        LnkdLst lista = new LnkdLst();
        System.out.println("Attempting to remove from empty list:");
        lista.remove(0);
        lista.print();
        System.out.println("Adding students to the list:");
        lista.add("Linus",90);
        lista.add("Ada",95);
        lista.add("Paul",78);
        lista.add("Grace",100);
        lista.print();
        Student s = lista.retrieve("Ada");
        if (s != null) {
            System.out.println("Found: " + s.name + " - " + s.score);
        } else {
            System.out.println("Student not found");
        }
        System.out.println("Attempting to remove the second student:");
        lista.remove(1);
        System.out.println("After removing the second student:");
        lista.print();
    }
    
}