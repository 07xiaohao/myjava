package com.example;



public class test {
    public static void main(String[] args) {

            for (int j = 5; j >= 1; j--) {
                for (int k = 1; k <= j - 1; k++) {
                    System.out.print ("-");

                }

                    if (j==5){
                        System.out.print ("+");
                    }
                    if (j==4){
                        System.out.print ("+++");
                    }
                    if (j==3){
                        System.out.print ("+++++");
                    }
                    if (j==2){
                        System.out.print ("+++++++");
                    }
                    if (j==1){
                        System.out.print ("+++++++++");
                    }
                for (int k = 1; k <= j - 1; k++) {
                    System.out.print ("-");

                }
                System.out.println ();



        }
    }
}
