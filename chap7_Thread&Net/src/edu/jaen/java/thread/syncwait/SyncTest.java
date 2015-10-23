package edu.jaen.java.thread.syncwait;
class SyncTest {
	public static void main(String[] args){
		//MainData m = new MainData();
		MainData m = new MainData3();

		IncThread t1 = new IncThread(m,"공급자 A");
		IncThread t3 = new IncThread(m,"공급자 B");

		DecThread t2 = new DecThread(m,"구매자 a");
		DecThread t4 = new DecThread(m,"구매자 b");
		
		t1.start();		
		t2.start();	
		t3.start();		
		t4.start();	
	
	}
}
