package edu.jaen.java.xml.sax;


import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAXParser {
	ArrayList<Check> list;

	public ArrayList<Check> getContent(String url){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			MyHandler handler = new MyHandler();
			parser.parse(new URL(url).openConnection().getInputStream(), handler);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public class MyHandler extends DefaultHandler{
		private StringBuilder sb;
		Check ch;
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
			sb.append(ch, start, length);
		}
		@Override
		public void endElement(String uri, String localName, String name)
				throws SAXException {
			super.endElement(uri, localName, name);
			if (this.ch != null){
				if (name.equalsIgnoreCase("Clean")){
					ch.setClean(sb.toString().trim());
				} else if (name.equalsIgnoreCase("Ready")){
					ch.setReady(sb.toString().trim());
				} else if (name.equalsIgnoreCase("Response")){
					ch.setResponse(sb.toString().trim());
				} else if (name.equalsIgnoreCase("Request")){
					sb.trimToSize();
					if(sb.length()>0)
						ch.setRequest(sb.toString().trim());
				} else if (name.equalsIgnoreCase("Check")){
				     list.add(ch);
				}
				sb.setLength(0);	
			}
		}
		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			list = new ArrayList<Check>();
			sb = new StringBuilder();
		}
		@Override
		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, name, attributes);
			if (name.equalsIgnoreCase("Check")){
				ch = new Check();
				ch.setCode(attributes.getValue(0));
			}
	}
}
}