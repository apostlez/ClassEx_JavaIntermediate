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
	/** singleton parttern을 위해 객체 생성 */
	private static ActivityManager am = new ActivityManager();
	/** Manifest.xml 을 parsing 한 데이타 저장객체 */
	private ArrayList<Manifest> parseList;
	/** 실행중인 component 저장객체 */
	private HashMap<String, ComponentKind> activeCom = new HashMap<String, ComponentKind>();

	private final static int ACTIVITY = 1;
	private final static int SERVICE = 2;

	/** singleton parttern을 위한 생성자 */
	private ActivityManager() {
	}

	/** singleton parttern을 위한 객체 획득 메소드오버로드 */
	public static ActivityManager getInstance() {
		return am;
	}

	/** ActivityManager 실행 메소드 */
	public void managerAction() {
		// loadManifest() 를 실행하여 Manifest.xml 을 로딩한
		// executeComponent() 를 실행하여 등록되어 있는 Component를 실행한다
		loadManifest();
		executeComponent();
	}

	/** 실행중인 컴폰언트 저장 객체 */
	public HashMap<String, ComponentKind> getActiveComponent() {
		return activeCom;
	}

	/**
	 * Manifest.xml 파일을 파싱하는 메서드 MySAXParser 클래스를 이용하여 파싱
	 */
	public  void loadManifest() {
		//MySAXParser 객체를 이용하여 Manifest.xml 을 파싱한다
		parseList = ManifestParser.getInstance().parse("Manifest.xml");
	}
    public   List<Manifest>  getParseList(){
    	return parseList;
    }
	
	/**
	 * Manifest.xml에 지정한 initstart 속성이 true인 framework component를 수행시키는 메서드
	 */
	private void executeComponent() {
		// manifest 에 있는 객체들을 속성에 맞게 실행한다
		for (Manifest m : parseList) {
			// 실행시킬 컴포넌트 이름
			String comName = m.getName();

			// comName이 이미 실행중인 component라 실행하지 안는다
			if (activeCom.get(comName) != null) {
				System.out.println("이미 실행중인 component는 다시 실행하지 안는다");
				continue;
			}

			// loadClass()를 이용하여 객체들 생성하고 실행한다
			if (m.getComponentKnd().equalsIgnoreCase("activity") && m.getInitstart().equals("true")) {
				Context context = (Context) newClass(comName);
				context.execute(ACTIVITY);
				// 실행시킨 component는 activeCom에 cmpName을 키값으로 저장한다
				activeCom.put(comName, new ComponentKind(ACTIVITY, comName));

			} else if (m.getComponentKnd().equalsIgnoreCase("service") && m.getInitstart().equals("true")) {

				Context context = (Context) newClass(comName);
				context.execute(SERVICE);

				// 실행시킨 component는 activeCom에 cmpName을 키값으로 저장한다
				activeCom.put(comName, new ComponentKind(SERVICE, comName));

			}
		}
	}

	/** User Component가 다른 component를 실행 시키려 할 경우 사용되는 오버로드 메소드 */
	public void executeComponent(String componentName, int componentKnd) {
		boolean flag = false;
		for (Manifest  m:  parseList) {

			if (m.getName().equals(componentName)) {
				// -----3단계 :
				// compenentName과 parseList에 있는 componentName을 비교하여
				// 같으면 loadClass()를 이용하여 Context를 생성하고
			    
				// 구현하세요~~~#################################################33
				// comName이 이미 실행중인 component라 실행하지 안는다
				if (activeCom.get(componentName) != null) {
					System.out.println("이미 실행중인 component는 다시 실행하지 안는다");
					flag = true;
				} else {
					Context c = (Context) newClass(componentName);
					if (componentKnd == 1) {
						c.execute(ACTIVITY);
						// 실행시킨 component는 activeCom에 cmpName을 키값으로 저장한다
						activeCom.put(componentName, new ComponentKind(ACTIVITY, componentName));
					} else if (componentKnd == 2) {
						c.execute(SERVICE);
						// 실행시킨 component는 activeCom에 cmpName을 키값으로 저장한다
						activeCom.put(componentName, new ComponentKind(SERVICE, componentName));
					}
					flag = true;
				}
				break;
			}
		}

		if (!flag) {
			// -----3단계 :
			// MessageDialog 객체를 생성하고 창제목으로는 "경 고"를 메세지로는
			// "컴폰언트가 등록되어있지 안습니다." 라고 한다
			// 구현하세요~~~~#################################################33
			MessageDialog md = new MessageDialog(getFrame(), "경 고");
			md.show("컴폰언트가 등록되어있지 안습니다.");
		}
	}

	/**
	 * framework Component를 로드하기 위한 메서드
	 * 
	 * @param name
	 *            로드할 framework Component 이름으로 Manifest.xml에 설정한 name
	 * @return 생성된 framework Component 객체
	 * 
	 *         reflaction을 이용하여 객체를 생성하여 리턴한다
	 */
	private Object newClass(String name) {
		Class c;
		Object obj = null;
		String fileName = "edu.jaen.java.framework.app." + name;

		try {
			// -----3단계 : name에 해당하는 클래스를 리플렉션을 이용하여 객체생성한다.
			// 단 패키지이름은 edu.jaen.java.framework.app 로 지정한다.
			c = Class.forName(fileName);
			// -----3단계 : 로드한 클래스로 부터 객체 생성한다.
			// 구현하세요~~~~#################################################33
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

	//실행중인 컴포넌트를 저장하기 위한 VO
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
