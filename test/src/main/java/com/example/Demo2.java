package com.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        List<String> list=new ArrayList<> ();
        FileInputStream fileInputStream=new FileInputStream ("E:\\a");
        InputStreamReader inputStreamReader=new InputStreamReader (fileInputStream,"utf-8");
        BufferedReader bufferedReader=new BufferedReader (inputStreamReader);
        String line="";
        while ((line=bufferedReader.readLine ())!=null){
            list.add (line);
        }
        fileInputStream.close ();
        inputStreamReader.close ();
        bufferedReader.close ();
    }
}
