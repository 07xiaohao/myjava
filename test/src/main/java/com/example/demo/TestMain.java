package com.example.demo;

public class TestMain {
    public static ReadString read;

    public static void main(String[] args) {
        Object lock=new Object ();
        read=new ReadString ();
        new ReadThead (0, read, lock).start ();
        new ReadThead (1, read, lock).start ();
        new ReadThead (2, read, lock).start ();

    }
}
