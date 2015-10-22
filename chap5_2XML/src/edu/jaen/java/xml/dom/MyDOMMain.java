package edu.jaen.java.xml.dom;

import java.util.ArrayList;


public class MyDOMMain {
    public static void main(String[] args){

        ArrayList<Check> list=new MyDOMParser().getContent("http://192.168.10.57:8080/result.xml");
        for(  Check c: list){
        	System.out.println(c);
        }
    }
   }
