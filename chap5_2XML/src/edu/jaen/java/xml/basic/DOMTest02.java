package edu.jaen.java.xml.basic;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class DOMTest02 {
   public static void main(String args[]) throws Exception {
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = dbf.newDocumentBuilder();
	  
	  System.out.println("parser class name : "+parser.getClass().getName()+"\n");
     
	  Document xmldoc = parser.parse("addr.xml");
      Element root = xmldoc.getDocumentElement();
      System.out.println(root);
   }
}

