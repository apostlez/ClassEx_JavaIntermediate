package edu.jaen.java.ref.xml;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.*;
import org.xml.sax.*;
public class NewInstanceSAX {
	public static ArrayList<Object> getInstance (String xmlPath, Object obj)throws Exception{
		Class c = obj.getClass();
		
		SAXParserFactory  factory = SAXParserFactory.newInstance();
		SAXParser  parser = factory.newSAXParser();
		SAXHandler  handler = new SAXHandler(c.getName());
		parser.parse(xmlPath, handler);
		
		Method[] m = c.getDeclaredMethods();
		ArrayList<ArrayList<RefValue>> list = handler.getList();
		System.out.println(list);
		
		ArrayList<Object > returnValue = new ArrayList<Object>();
		for (ArrayList<RefValue> vo : list) {
			Object o = c.newInstance();
			for (RefValue refValue : vo) {
				String name = refValue.getName();
				String type = refValue.getType();
				Object value= null;
				if(type.equalsIgnoreCase("byte")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("short")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("integer")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("long")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("float")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("double")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("boolean")) value = new Integer(refValue.getValue());
				else if(type.equalsIgnoreCase("charactor")) value = new Integer(refValue.getValue());
				else  value = refValue.getValue();
				for (Method method : m) {
					if(method.getName().equalsIgnoreCase("set"+name)) method.invoke(o, value);
				}
			}
			returnValue.add(o);
		}
		return returnValue;
	}
}
