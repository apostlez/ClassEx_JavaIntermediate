package edu.jaen.java.framework.component;

import edu.jaen.java.framework.manager.ActivityManager;


public abstract class Context {
	private final static int ACTIVITY = 1;
	private final static int SERVICE = 2;
	/**
	 * Activity를 수행시키는 메서드
	 * 
	 * @param activityName
	 *            수행시킬 activityName으로 Manifest.xml에 정의한 name
	 */
	public void startActivity(String activityName) {
		ActivityManager.getInstance().executeComponent(activityName, 1);
	}

	/** */
	public void stopActivity() {
	}

	public void startService() {
	}

	public void stopService() {
	}

	/** component를 수행시 실행되는 메서드로 해당 컴포넌트에 필요할 역할을 수행한다. */
	public void execute(int comKnd) {
		switch (comKnd) {
		case ACTIVITY:
			//-----1단계 :
			// activity실행 메소드 executeActivity()을 실행한다
			executeActivity();
			break;
		case SERVICE:
			//-----1단계 :
			// service실행 메소드 executeService()를 실행한다
			executeService();
			break;
		default:
			break;
		}
		 
	}

	private void executeActivity() {
		try {
			Activity activity = (Activity) this;
			activity.onCreate();
			activity.onStart();
			
		} catch (NullPointerException e) {
			System.out.println("Not Find Activity....");
		}
	}

	private void executeService() {
		try {
			Service service = (Service) this;
			service.onCreate();
			service.onStart();
		} catch (NullPointerException e) {
			System.out.println("Not Find Service....");
		}
	}

}
