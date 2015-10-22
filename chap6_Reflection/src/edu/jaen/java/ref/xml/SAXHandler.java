package edu.jaen.java.ref.xml;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class SAXHandler extends DefaultHandler {
	String temp, tagName;
	ArrayList<RefValue> vo;
	ArrayList<ArrayList<RefValue>> list;
	RefValue value;
	boolean isRootTag = true;
	public SAXHandler(String tagName){
		if(tagName!=null && tagName.lastIndexOf(".")>0){
			this.tagName = tagName.substring(tagName.lastIndexOf(".")+1);
		}
		list =  new ArrayList<ArrayList<RefValue>>();
	}
	public void characters(char[] ch, int start, int length){
		temp = new String(ch, start, length);
	}
	
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase(tagName)){
			vo = new ArrayList<RefValue>();
		}
		else{
			if(isRootTag){
				isRootTag = false;
				return;
			}else{
				value = new RefValue(qName, attributes.getValue("value"), attributes.getValue("type"));
				vo.add(value);
			}
		} 
	}
	public void endElement(String uri, String lName, String qName){
		if(qName.equalsIgnoreCase(tagName))list.add(vo);
	}
	public ArrayList<ArrayList<RefValue>> getList(){return list;}
}
