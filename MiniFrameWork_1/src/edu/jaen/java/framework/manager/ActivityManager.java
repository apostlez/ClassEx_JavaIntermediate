package edu.jaen.java.framework.manager;

import java.awt.Dialog;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.jaen.java.framework.component.Activity;
import edu.jaen.java.framework.component.Context;
import edu.jaen.java.framework.component.Service;
import edu.jaen.java.framework.util.parser.Manifest;
import edu.jaen.java.framework.util.parser.ManifestParser;

public class ActivityManager {
	/** singleton parttern�� ���� ��ü ���� */
	private static ActivityManager am = new ActivityManager();
	/** Manifest.xml �� parsing �� ����Ÿ ���尴ü */
	private ArrayList<Manifest> parseList;
	/** �������� component ���尴ü */
	private HashMap<String, ComponentKind> activeCom = new HashMap<String, ComponentKind>();

	private final static int ACTIVITY = 1;
	private final static int SERVICE = 2;

	/** singleton parttern�� ���� ������ */
	private ActivityManager() {
	}

	/** singleton parttern�� ���� ��ü ȹ�� �޼ҵ�����ε� */
	public static ActivityManager getInstance() {
		return am;
	}

	/** ActivityManager ���� �޼ҵ� */
	public void managerAction() {
		// loadManifest() �� �����Ͽ� Manifest.xml �� �ε���
		// executeComponent() �� �����Ͽ� ��ϵǾ� �ִ� Component�� �����Ѵ�
		loadManifest();
		executeComponent();
	}

	/** �������� ������Ʈ ���� ��ü */
	public HashMap<String, ComponentKind> getActiveComponent() {
		return activeCom;
	}

	/**
	 * Manifest.xml ������ �Ľ��ϴ� �޼��� MySAXParser Ŭ������ �̿��Ͽ� �Ľ�
	 */
	public  void loadManifest() {
		//MySAXParser ��ü�� �̿��Ͽ� Manifest.xml �� �Ľ��Ѵ�
		parseList = ManifestParser.getInstance().parse("Manifest.xml");
	}
    public   List<Manifest>  getParseList(){
    	return parseList;
    }
	
	/**
	 * Manifest.xml�� ������ initstart �Ӽ��� true�� framework component�� �����Ű�� �޼���
	 */
	private void executeComponent() {
		// manifest �� �ִ� ��ü���� �Ӽ��� �°� �����Ѵ�
		for (Manifest m : parseList) {
			// �����ų ������Ʈ �̸�
			String comName = m.getName();

			// comName�� �̹� �������� component�� �������� �ȴ´�
			if (activeCom.get(comName) != null) {
				System.out.println("�̹� �������� component�� �ٽ� �������� �ȴ´�");
				continue;
			}

			// loadClass()�� �̿��Ͽ� ��ü�� �����ϰ� �����Ѵ�
			if (m.getComponentKnd().equalsIgnoreCase("activity") && m.getInitstart().equals("true")) {
				Context context = (Context) newClass(comName);
				context.execute(ACTIVITY);
				// �����Ų component�� activeCom�� cmpName�� Ű������ �����Ѵ�
				activeCom.put(comName, new ComponentKind(ACTIVITY, comName));

			} else if (m.getComponentKnd().equalsIgnoreCase("service") && m.getInitstart().equals("true")) {

				Context context = (Context) newClass(comName);
				context.execute(SERVICE);

				// �����Ų component�� activeCom�� cmpName�� Ű������ �����Ѵ�
				activeCom.put(comName, new ComponentKind(SERVICE, comName));

			}
		}
	}

	/** User Component�� �ٸ� component�� ���� ��Ű�� �� ��� ���Ǵ� �����ε� �޼ҵ� */
	public void executeComponent(String componentName, int componentKnd) {
		boolean flag = false;
		for (Manifest  m:  parseList) {

			if (m.getName().equals(componentName)) {
				// -----3�ܰ� :
				// compenentName�� parseList�� �ִ� componentName�� ���Ͽ�
				// ������ loadClass()�� �̿��Ͽ� Context�� �����ϰ�
			    
				// �����ϼ���~~~#################################################33
				// comName�� �̹� �������� component�� �������� �ȴ´�
				if (activeCom.get(componentName) != null) {
					System.out.println("�̹� �������� component�� �ٽ� �������� �ȴ´�");
					flag = true;
				} else {
					Context c = (Context) newClass(componentName);
					if (componentKnd == 1) {
						c.execute(ACTIVITY);
						// �����Ų component�� activeCom�� cmpName�� Ű������ �����Ѵ�
						activeCom.put(componentName, new ComponentKind(ACTIVITY, componentName));
					} else if (componentKnd == 2) {
						c.execute(SERVICE);
						// �����Ų component�� activeCom�� cmpName�� Ű������ �����Ѵ�
						activeCom.put(componentName, new ComponentKind(SERVICE, componentName));
					}
					flag = true;
				}
				break;
			}
		}

		if (!flag) {
			// -----3�ܰ� :
			// MessageDialog ��ü�� �����ϰ� â�������δ� "�� ��"�� �޼����δ�
			// "������Ʈ�� ��ϵǾ����� �Ƚ��ϴ�." ��� �Ѵ�
			// �����ϼ���~~~~#################################################33
			MessageDialog md = new MessageDialog(getFrame(), "�� ��");
			md.show("������Ʈ�� ��ϵǾ����� �Ƚ��ϴ�.");
		}
	}

	/**
	 * framework Component�� �ε��ϱ� ���� �޼���
	 * 
	 * @param name
	 *            �ε��� framework Component �̸����� Manifest.xml�� ������ name
	 * @return ������ framework Component ��ü
	 * 
	 *         reflaction�� �̿��Ͽ� ��ü�� �����Ͽ� �����Ѵ�
	 */
	private Object newClass(String name) {
		Class c;
		Object obj = null;
		String fileName = "edu.jaen.java.framework.app." + name;

		try {
			// -----3�ܰ� : name�� �ش��ϴ� Ŭ������ ���÷����� �̿��Ͽ� ��ü�����Ѵ�.
			// �� ��Ű���̸��� edu.jaen.java.framework.app �� �����Ѵ�.
			c = Class.forName(fileName);
			// -----3�ܰ� : �ε��� Ŭ������ ���� ��ü �����Ѵ�.
			// �����ϼ���~~~~#################################################33
			obj = c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

	//�������� ������Ʈ�� �����ϱ� ���� VO
	public class ComponentKind {
		private int ComponenetKnd;
		private String ComponentName;
		private int state;

		public ComponentKind(int componenetKnd, String componentName, int state) {
			super();
			ComponenetKnd = componenetKnd;
			ComponentName = componentName;
			this.state = state;
		}

		public ComponentKind(int componenetKnd, String componentName) {
			super();
			ComponenetKnd = componenetKnd;
			ComponentName = componentName;
		}

		@Override
		public String toString() {
			return "ComponentKind [ComponenetKnd=" + ComponenetKnd + ", ComponentName=" + ComponentName + ", state="
					+ state + "]";
		}
	}

	public Frame getFrame() {
		return new Frame();
	}
	
	
}
