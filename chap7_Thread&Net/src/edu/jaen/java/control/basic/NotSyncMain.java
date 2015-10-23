package edu.jaen.java.control.basic;

public class NotSyncMain {
	
	public static NotSyncData data = new NotSyncData();;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("시작");
		new IncreOne().start();
		new IncreTwo().start();
		System.out.println("종료");
	}
}

class NotSyncData{
	int i;
}

class IncreOne extends Thread{
	public void run(){
		for (int i = 0; i < 1000000; i++) {
			NotSyncMain.data.i++;
		}
		System.out.println("IncreOne i = "+NotSyncMain.data.i);
	}
}

class IncreTwo extends Thread{
	public void run(){
		for (int i = 0; i < 1000000; i++) {
			NotSyncMain.data.i++;
		}
		System.out.println("IncreTwo i = "+NotSyncMain.data.i);
	}
}