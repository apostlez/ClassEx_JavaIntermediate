package edu.jaen.java.framework.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.jaen.java.framework.manager.ActivityManager;
import edu.jaen.java.framework.manager.ActivityManager.ComponentKind;

public class Activity extends Context {
	boolean init_state = false;
	/** Activity�� ���� �⺻ ȭ�� Frame */
	Frame frame;
	/**
	 * Activity�� ǥ���� UI Component�� xml�� ������ id�� map�� �����Ͽ� ������
	 * findByViewId(String id) �޼��忡 ���� ������ �� �ִ�.
	 */
	HashMap<String, Component> cmpmap = new HashMap<String, Component>();

	public Activity() {
	}

	/**
	 * Activity ȭ���� �����ϴ� �޼���
	 * 
	 * @param xml
	 *            ������ layout xml
	 * @throws ActivityUIException
	 */
	public void setContentView(String xml) throws ActivityUIException {
		// ���� ��ü ���� (java.io.File)
		// file ��� : "./layout/" + xml + ".xml"
		File file = new File("./layout/" + xml + ".xml");

		// xml�� ������ �����ϰ� �����̸� ������ ���� �� �ִٸ�
		// Activity class�� �ִ� loadLayout()
		// ���ϰ�ü�� �Ѱ���
		// ������ �дٰ� ������ �߻��ϰų� xml�� ������ �ƴѰ��
		// ActivityUIException�� �߻���Ų��.
		if (file.exists() && file.isFile() && file.canRead()) {
			try {
				loadLayout(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActivityUIException(e.getMessage());
			}
		} else {
			throw new ActivityUIException("layout xml " + xml
					+ " ������ Loading�Ҽ� �����ϴ�.");
		}
	}

	/**
	 * layout xml ������ DOM �ļ��� �̿��� �Ľ��ϱ�
	 * 
	 * @param xml
	 *            �Ľ��� xml ���� �̸�
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void loadLayout(File xml) throws ParserConfigurationException,
			SAXException, IOException {
		// -----2�ܰ� DOM : �ļ� �����Ѵ�.
		/* DOM parser ���� */
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		
		
		// -----2�ܰ� DOM : �ļ��� ���� root Element �����Ѵ�.		
		//�����ϼ���~***********************************************************
		DocumentBuilder dBuilder = null;
		Document doc = null;
		Element root =null;
		
		
		/* root.normalize();���� �±׻����� ������ ������ �ְ�, ����ִ� ������Ʈ�� �� �±׷� �ٲ��ش� */
		root.normalize();

		/* createUIComponent()�� �̿��Ͽ� parsing�� node�� UI ��ü�� �����Ѵ� */
		Component cmp = createUIComponent(root, frame);
		/*
		 * ������ ��ü�� Container �迭�̸� loadChild()�� �̿��Ͽ� �����ϰ� �ִ� UI Component�� ��ü�����Ͽ�
		 * �θ�ü�� �����Ѵ�
		 */
		if (cmp instanceof Container) {
			loadChild(root, (Container) cmp);
		}

		frame.setVisible(true);
	}

	/**
	 * layout xml�� ������ UI component�� ���÷����� �̿��Ͽ� �����ϰ� XML attribute ���� parsing�Ͽ�
	 * UI component�� �Ӽ����� �ְ� Container�� �߰��ϴ� �޼���
	 * 
	 * @param node
	 *            layout xml�� ���õǾ� ������ UI Component
	 * @param container
	 *            ������ UI Component�� ��� Container
	 * @return node�� ���� ������ UI Component
	 */
	private Component createUIComponent(Element node, Container container) {
		Component cmp = null;
		Class clazz = null;
		Object obj = null;
		// -----3�ܰ� : node �̸��� �ش��ϴ� Ŭ���� �ε��ϱ�
		try {
			// ���÷����� �̿��Ͽ� node�� UI��ü�� �����Ѵ�
			// node �̸��տ� package�� "java.awt"�� �ٿ���
			clazz = Class.forName("java.awt." + node.getNodeName());
			System.out.println(node.getNodeName());
			obj = clazz.newInstance();

			// -----3�ܰ� : �ε��� Ŭ������ ���� ��ü �����ϱ�
			// ������ ��ü�� UI��ü�̸� XML attribute ���� parsing�Ͽ� UI component�� �Ӽ����� �ش�
			// -----3�ܰ� DOM : node�� ������ �Ӽ����� �����Ѵ�.
			if (obj instanceof Component) {
				cmp = (Component) obj;
				NamedNodeMap attrs = node.getAttributes();
				
				for (int idx = 0; idx < attrs.getLength(); idx++) {
					Node attr = attrs.item(idx);
					String attName = attr.getNodeName();
					String attValue = attr.getNodeValue();

					// ���÷����� Method[] �� �̿��Ͽ� "set"���� �����ϴ� �޼ҵ带 invoke() ��Ų��
					Method[] methods = clazz.getMethods();
					for (Method method : methods) {
						
						if (method.getName().equalsIgnoreCase("set" + attName)) {
							System.out.println(method.getName());
							try {
								method.invoke(cmp, attValue);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			// �����̳ʿ� ������ UI�� �����Ѵ�
			container.add(cmp);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cmp;
	}

	/**
	 * layout xml�� ������ UI Component�� Container�� ��� ���� UI Component�� �������ִ� �޼���
	 * 
	 * @param parentNode
	 *            UI Component�� ��� �ִ� Container�� xml node
	 * @param container
	 *            UI Component�� ��� �ִ� Container ��ü
	 */
	private void loadChild(Node parentNode, Container container) {
		/*
		 * ���������� ���ͼ� NodeType�� ELEMENT_NODE �� �͵��� createUIComponent() �� �̿��Ͽ�
		 * ��ü�����ϰ� ������ ��ü�� cmpmap�� Attribute�� id�� Ű������ �����Ѵ�.
		 * (cmpmap�� ������ activity���� findViewById() �� �̿��Ͽ��˻��Ҽ� �ֵ��� �Ѵ�)
		 */

		//�����ϼ���~~*********************************************
	}

	/**
	 * Activity ȭ�鿡 ���õ� UI Component�� id�� ���� ã�� �޼���
	 * 
	 * @param id
	 *            ã�� UI Component id
	 * @return ã�� UI Component
	 */
	public Component findViewById(String id) {
		Component cmp = cmpmap.get(id);
		return cmp;
	}

	/**
	 * component�� ����� ����Ǵ� �޼���� �ش� ������Ʈ�� �ʿ��� ������ �����Ѵ�. Activity�� View�� ����ϴ�
	 * component�̹Ƿ� �̸� ȭ���� �����Ѵ�.
	 * */
	public void onCreate() {
		System.out.println("Activity onCreate..");
		frame = ActivityManager.getInstance().getFrame();
		frame.setTitle(getClass().getSimpleName());
		frame.setSize(300, 600);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {

			// life cycle�� ���� �̺�Ʈ ó��
			@Override
			public void windowClosing(WindowEvent e) {
				/*
				 * Activity�� lifecycle onStop()�� onDestroy()�� ȣ���Ѵ�
				 */
				onStop();
				onDestroy();
				// Frame�� ȭ�鿡�� ��������� �Ѵ�
				frame.setVisible(false);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				/*
				 * Activity�� lifecycle onStart()�� ȣ���Ѵ�
				 */
				if (init_state)
					onStart();

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				/*
				 * Activity�� lifecycle onStop()�� ȣ���Ѵ�
				 */
				if (init_state)
					onStop();

			}
		});
	}

	public void onStart() {
		init_state = true;
	}

	public void onStop() {
		init_state = false;
	}

	/*
	 * ȭ���� ������� ����Ǵ� �޼ҵ� ActivityManager�� ���� �������� ��ü ����� ������ �����Ǵ� ��ü�� �����Ѵ�
	 */
	public void onDestroy() {
		HashMap<String, ComponentKind> activeCom = ActivityManager
				.getInstance().getActiveComponent();
		activeCom.remove(this.getClass().getSimpleName());
	}
}
