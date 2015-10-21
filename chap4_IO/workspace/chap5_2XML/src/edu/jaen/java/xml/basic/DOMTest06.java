package edu.jaen.java.xml.basic;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMTest06 {
   public static void main(String args[]) throws Exception {
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = dbf.newDocumentBuilder();
      Document xmldoc = parser.parse("addr.xml");
      Element root = xmldoc.getDocumentElement();
      getNode(root);
   }
   public static void getNode(Node n) {
      for(Node ch = n.getFirstChild();ch != null;ch = ch.getNextSibling()) {
         //요소를 처리한다
         if(ch.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(ch.getNodeName());
            getNode(ch);
         }
         //텍스트를 처리한다
         else if(ch.getNodeType() == Node.TEXT_NODE
                     && ch.getNodeValue().trim().length() != 0){
            System.out.println(ch.getNodeValue());
         }
      }
   }
}


