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
        this.score = score;
        this.next = null;
    }
}

class lnkdlst {
    Student head;
    public lnkdlst() //Constructor de la clase 
    {
        this.head = null;
    }
    
    public void add(String name, int score) {
        Student newStudent = new Student(name,score);
        if(head == null) {
            head = newStudent;
            return;
        }
        Student current = head;
        while(current.next != null) {
            current = current.next;
        }
        current.next = newStudent;
    }
    public void print() {
        Student current = this.head;
        while(current !=null) {
            System.out.println(current.name + " - " + current.score);
            current = current.next;
        }
    }
    public Student retrieve(String searchedName) {
        Student current = this.head;
        while(current != null) {
            if(current.name.equals(searchedName)) {
                return current;
            }
            current = current.next; //traverse the list
        }
        return null; //not found
    }
}

public class PruebaLinkedlist {

    public static void main(String[] args) {
        lnkdlst lista = new lnkdlst();
        lista.add("Linus", 90);
        lista.add("Ada", 95);
        lista.add("Paul", 78);
        lista.print();
        Student found = lista.retrieve("Ada");
        System.out.println("Found" + found.name + "score: " + found.score);
        Student notfound = lista.retrieve("Gus");
        if(notfound != null) {
            System.out.println("Found" + found.name + "score: " + found.score);
        }
    }
}