package com.example.demo;

public class ReadString {
    public static int[] read ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
    public synchronized void read(int id,int index){
        if (index>29){
            return;
        }
        System.out.print ("Thead:"+id+"read:"+read[index]+"\n");
    }
}
