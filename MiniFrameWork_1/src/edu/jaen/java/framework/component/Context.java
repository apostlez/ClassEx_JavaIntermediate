package edu.jaen.java.framework.component;

import edu.jaen.java.framework.manager.ActivityManager;


public abstract class Context {
	private final static int ACTIVITY = 1;
	private final static int SERVICE = 2;
	/**
	 * Activity�� �����Ű�� �޼���
	 * 
	 * @param activityName
	 *            �����ų activityName���� Manifest.xml�� ������ name
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

	/** component�� ����� ����Ǵ� �޼���� �ش� ������Ʈ�� �ʿ��� ������ �����Ѵ�. */
	public void execute(int comKnd) {
		switch (comKnd) {
		case ACTIVITY:
			//-----1�ܰ� :
			// activity���� �޼ҵ� executeActivity()�� �����Ѵ�
			executeActivity();
			break;
		case SERVICE:
			//-----1�ܰ� :
			// service���� �޼ҵ� executeService()�� �����Ѵ�
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
