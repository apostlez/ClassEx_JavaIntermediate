package edu.jaen.java.xml.stream;

import java.util.ArrayList;

public class MyStreamMain {
    public static void main(String[] args){

        ArrayList<Check> list=new MyStreamParser().getList("http://127.0.0.1:8080/result.txt");
        for(  Check c: list){
        	System.out.println(c);
        }
    }
   }
