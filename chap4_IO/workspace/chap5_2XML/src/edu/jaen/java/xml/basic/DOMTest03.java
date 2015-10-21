package edu.jaen.java.xml.basic;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class DOMTest03 {
   public static void main(String args[]) throws Exception {
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = dbf.newDocumentBuilder();
      Document xmldoc = parser.parse("addr.xml");
      Element root = xmldoc.getDocumentElement();
    //  System.out.println(root);
        getNode(root);
   }
   //노드의 자식(child)노드를 찾는 메서드
   public static void getNode(Node n) {
      for(Node ch = n.getFirstChild();ch != null;ch = ch.getNextSibling()) {
         System.out.println(ch.getNodeName());
      }
   }
}

