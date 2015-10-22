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
	/** Activity를 위한 기본 화면 Frame */
	Frame frame;
	/**
	 * Activity에 표현된 UI Component를 xml에 지정한 id로 map에 저장하여 관리함
	 * findByViewId(String id) 메서드에 의해 추출할 수 있다.
	 */
	HashMap<String, Component> cmpmap = new HashMap<String, Component>();

	public Activity() {
	}

	/**
	 * Activity 화면을 셋팅하는 메서드
	 * 
	 * @param xml
	 *            셋팅할 layout xml
	 * @throws ActivityUIException
	 */
	public void setContentView(String xml) throws ActivityUIException {
		// 파일 객체 생성 (java.io.File)
		// file 경로 : "./layout/" + xml + ".xml"
		File file = new File("./layout/" + xml + ".xml");

		// xml이 파일이 존재하고 파일이며 파일을 읽을 수 있다면
		// Activity class에 있는 loadLayout()
		// 파일객체를 넘겨준
		// 파일을 읽다가 오류가 발생하거나 xml이 파일이 아닌경우
		// ActivityUIException을 발생시킨다.
		if (file.exists() && file.isFile() && file.canRead()) {
			try {
				loadLayout(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActivityUIException(e.getMessage());
			}
		} else {
			throw new ActivityUIException("layout xml " + xml
					+ " 파일을 Loading할수 없습니다.");
		}
	}

	/**
	 * layout xml 파일을 DOM 파서를 이용해 파싱하기
	 * 
	 * @param xml
	 *            파싱할 xml 파일 이름
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private void loadLayout(File xml) throws ParserConfigurationException,
			SAXException, IOException {
		// -----2단계 DOM : 파서 생성한다.
		/* DOM parser 생성 */
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		
		
		// -----2단계 DOM : 파서를 통해 root Element 추출한다.		
		//구현하세요~***********************************************************
		DocumentBuilder dBuilder = null;
		Document doc = null;
		Element root =null;
		
		
		/* root.normalize();실행 태그사이의 공백을 제거해 주고, 비어있는 엘리먼트는 빈 태그로 바꿔준다 */
		root.normalize();

		/* createUIComponent()를 이용하여 parsing한 node를 UI 객체로 생성한다 */
		Component cmp = createUIComponent(root, frame);
		/*
		 * 생성된 객체가 Container 계열이면 loadChild()를 이용하여 포함하고 있는 UI Component도 객체생성하여
		 * 부모객체에 저장한다
		 */
		if (cmp instanceof Container) {
			loadChild(root, (Container) cmp);
		}

		frame.setVisible(true);
	}

	/**
	 * layout xml에 셋팅한 UI component를 리플렉스를 이용하여 생성하고 XML attribute 값을 parsing하여
	 * UI component의 속성값을 주고 Container에 추가하는 메서드
	 * 
	 * @param node
	 *            layout xml에 셋팅되어 생성할 UI Component
	 * @param container
	 *            생성된 UI Component가 담길 Container
	 * @return node를 통해 생성된 UI Component
	 */
	private Component createUIComponent(Element node, Container container) {
		Component cmp = null;
		Class clazz = null;
		Object obj = null;
		// -----3단계 : node 이름에 해당하는 클래스 로딩하기
		try {
			// 리플렉스를 이용하여 node를 UI객체로 생성한다
			// node 이름앞에 package명 "java.awt"를 붙여준
			clazz = Class.forName("java.awt." + node.getNodeName());
			System.out.println(node.getNodeName());
			obj = clazz.newInstance();

			// -----3단계 : 로딩한 클래스로 부터 객체 생성하기
			// 생성된 객체가 UI객체이면 XML attribute 값을 parsing하여 UI component의 속성값을 준다
			// -----3단계 DOM : node에 설정된 속성들을 추출한다.
			if (obj instanceof Component) {
				cmp = (Component) obj;
				NamedNodeMap attrs = node.getAttributes();
				
				for (int idx = 0; idx < attrs.getLength(); idx++) {
					Node attr = attrs.item(idx);
					String attName = attr.getNodeName();
					String attValue = attr.getNodeValue();

					// 리플렉션의 Method[] 를 이용하여 "set"으로 시작하는 메소드를 invoke() 시킨다
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
			// 컨테이너에 생성한 UI를 저장한다
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
	 * layout xml에 셋팅한 UI Component가 Container인 경우 하위 UI Component를 생성해주는 메서드
	 * 
	 * @param parentNode
	 *            UI Component를 담고 있는 Container의 xml node
	 * @param container
	 *            UI Component를 담고 있는 Container 객체
	 */
	private void loadChild(Node parentNode, Container container) {
		/*
		 * 하위노드들을 얻어와서 NodeType이 ELEMENT_NODE 인 것들을 createUIComponent() 를 이용하여
		 * 객체생성하고 생성한 객체를 cmpmap에 Attribute의 id를 키값으로 저장한다.
		 * (cmpmap은 구현된 activity에서 findViewById() 를 이용하여검색할수 있도록 한다)
		 */

		//구현하세요~~*********************************************
	}

	/**
	 * Activity 화면에 셋팅된 UI Component를 id를 통해 찾는 메서드
	 * 
	 * @param id
	 *            찾을 UI Component id
	 * @return 찾은 UI Component
	 */
	public Component findViewById(String id) {
		Component cmp = cmpmap.get(id);
		return cmp;
	}

	/**
	 * component를 수행시 실행되는 메서드로 해당 컴포넌트에 필요할 역할을 수행한다. Activity는 View를 담당하는
	 * component이므로 미리 화면을 생성한다.
	 * */
	public void onCreate() {
		System.out.println("Activity onCreate..");
		frame = ActivityManager.getInstance().getFrame();
		frame.setTitle(getClass().getSimpleName());
		frame.setSize(300, 600);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {

			// life cycle을 위한 이벤트 처리
			@Override
			public void windowClosing(WindowEvent e) {
				/*
				 * Activity의 lifecycle onStop()과 onDestroy()를 호출한다
				 */
				onStop();
				onDestroy();
				// Frame이 화면에서 사라지도록 한다
				frame.setVisible(false);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				/*
				 * Activity의 lifecycle onStart()를 호출한다
				 */
				if (init_state)
					onStart();

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				/*
				 * Activity의 lifecycle onStop()을 호출한다
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
	 * 화면이 사라질때 실행되는 메소드 ActivityManager로 부터 실행중인 객체 목록을 가져와 삭제되는 객체를 제거한다
	 */
	public void onDestroy() {
		HashMap<String, ComponentKind> activeCom = ActivityManager
				.getInstance().getActiveComponent();
		activeCom.remove(this.getClass().getSimpleName());
	}
}
