package edu.jaen.java.xml.sax;

import java.util.ArrayList;

public class MySaxMain {
	public static void main(String[] args){

		//192.168.10.57
        //ArrayList<Check> list=new MySAXParser().getContent("http://127.0.0.1:8080/result.xml");
		/*
		 *  install tomcat server
		 *  copy result.xml to webapp dir in tomcat
		 */
        ArrayList<Check> list=new MySAXParser().getContent("http://192.168.10.57:8080/result.xml");
        for(  Check c: list){
        	System.out.println(c);
        }
    }
}