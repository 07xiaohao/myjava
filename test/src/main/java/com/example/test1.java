package com.example;

public class test1 {
    public static void main(String[] args) {

        for (int j = 5; j >= 1; j--) {
            for (int k = 1; k <= j - 1; k++) {
                System.out.print ("-");

            }
            for (int i = 1; i <=-2*j+11; i++) {
                System.out.print ("+");
            }
            for (int k = 1; k <= j - 1; k++) {
                System.out.print ("-");

            }
            System.out.println ();
        }

    }
}
