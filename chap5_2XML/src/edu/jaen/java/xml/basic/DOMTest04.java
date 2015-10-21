package edu.jaen.java.xml.basic;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMTest04 {
   public static void main(String args[]) throws Exception {
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = dbf.newDocumentBuilder();
      Document xmldoc = parser.parse("addr.xml");
      Element root = xmldoc.getDocumentElement();
      getNode(root);
   }
   //노드의 자식(child)노드를 찾아간다
   public static void getNode(Node n) {
      for(Node ch = n.getFirstChild();ch != null;ch = ch.getNextSibling()) {
         //엘레먼트 정보만 출력
         if(ch.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(ch.getNodeName());
         }
      }
   }
}

