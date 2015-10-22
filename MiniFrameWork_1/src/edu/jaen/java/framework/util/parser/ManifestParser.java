package edu.jaen.java.framework.util.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

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
       // ���� �ϼ���~~~~~~~***********************************************************

	}

	public static ManifestParser getInstance() {
		return saxParser;
	}

	public ArrayList<Manifest> parse(String fileName) {
		try {
			
			
			
			// -----2�ܰ� SAX : SAX factory �� ���� parser�� ���Ѵ�
			// -----2�ܰ� SAX : SAX Handler�� �����Ѵ�.
			// -----2�ܰ� SAX : fileName ������ �Ľ��Ѵ�.

			// ���� �ϼ���~~~~~~~***********************************************************


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


			// ���� �ϼ���~~~~~~~***********************************************************

			
			
			
		}

	}
}
