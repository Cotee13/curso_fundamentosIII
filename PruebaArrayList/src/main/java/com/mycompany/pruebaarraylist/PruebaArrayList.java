/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pruebaarraylist;
import java.util.ArrayList;
//Author José Luis Bustamante Madrid
//Date 08-31-26

/**
 *
 * @author pinin
 */
public class PruebaArrayList {

    public static void main(String[] args) {
        ArrayList<String> personalities = new ArrayList <String>();
        personalities.add("Ada Lovelace");
        personalities.add("Alan Turing");
        personalities.add("Grace Hopper");
        String name =  personalities.get;
        System.out.println(name);
        System.println("The size of the Arraylist is: " + personalities.size);
        personalities.remove(0);
        personalities.set(1,"Margaret Hamilton");
        System.out.println(personalities);
    }
}
