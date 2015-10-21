package edu.jaen.java.xml.basic;
import java.io.FileOutputStream;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
public class DOMTest07 {
   public static void main(String args[]) throws Exception {
      DocumentBuilderFactory dbf
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = dbf.newDocumentBuilder();
      Document xmldoc = parser.parse("addr.xml");
      Element root = xmldoc.getDocumentElement();
      System.out.println(root);
      
      TransformerFactory factory= TransformerFactory.newInstance();
      Transformer tf=factory.newTransformer();
      tf.setOutputProperty("encoding","euc-kr");
      tf.setOutputProperty("indent","yes");
      DOMSource src=new DOMSource(xmldoc);
      //StreamResult result=new StreamResult(
      //								System.out);
      StreamResult result=new StreamResult(
    		    new FileOutputStream("out.xml"));
      tf.transform(src, result);
   }
}

