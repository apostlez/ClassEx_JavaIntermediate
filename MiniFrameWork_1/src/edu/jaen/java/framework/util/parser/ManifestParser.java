package edu.jaen.java.framework.util.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/** Manifest.xml ������ SAX �ļ��� �̿��Ͽ� �м��Ѵ�.*/
public class ManifestParser {
	private ArrayList<Manifest> parseList = new ArrayList<Manifest>();
	private static ManifestParser saxParser = new ManifestParser();
	SAXParserFactory factory ;
	SAXParser parser;

	private ManifestParser() {
		// -----2�ܰ� SAX : SAXParserFactory�� �����Ѵ�.
		factory = SAXParserFactory.newInstance();
	}

	public static ManifestParser getInstance() {
		return saxParser;
	}

	public ArrayList<Manifest> parse(String fileName) {
		try {
			// -----2�ܰ� SAX : SAX factory �� ���� parser�� ���Ѵ�
			// -----2�ܰ� SAX : SAX Handler�� �����Ѵ�.
			// -----2�ܰ� SAX : fileName ������ �Ľ��Ѵ�.
			parser = factory.newSAXParser();
			MySAXHandler handler = new MySAXHandler();
			parser.parse(new File(fileName), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseList;
	}

	private class MySAXHandler extends DefaultHandler {
		private Manifest mf;

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			parseList = new ArrayList<Manifest>();
		}

		@Override
		public void startElement(String uri, String localName, String name,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, name, attributes);
			// -----2�ܰ� SAX :  element name�� Activity�� Service �̸�
			// Manifest��ü�� �����ϰ� ���� �ִ´�.
			if(name.equalsIgnoreCase("activity") || name.equalsIgnoreCase("service")) {
				String n = attributes.getValue("name").toString().trim();
				Manifest mf = new Manifest(name, n);
				String initstart = attributes.getValue("initstart").toString().trim();
				mf.setInitstart(initstart);
				parseList.add(mf);
			}
		}
	}
}
