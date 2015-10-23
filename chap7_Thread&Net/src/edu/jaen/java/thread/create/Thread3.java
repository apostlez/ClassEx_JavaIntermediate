package edu.jaen.java.thread.create;
public class Thread3 {
	public static void main(String args[]) {
		Runnable r1 = new MyThread3();
		Runnable r2 = new MyThread3();
		Runnable r3 = new MyThread3();		
		Thread t1 = new Thread(r1,"One");
		Thread t2 = new Thread(r2,"Two");
		Thread t3 = new Thread(r3,"Three");

		/*
		Runnable r = new MyThread();
		Thread t1 = new Thread(r,"One");
		Thread t2 = new Thread(r,"Two");
		Thread t3 = new Thread(r,"Three");
		*/

		t1.start();
		t2.start();
		t3.start();
	}
}
class MyThread3 implements Runnable {
	private int cnt;
	private String name;
	
	public void run() {
		while(true) {
		  try {
				Thread.sleep(100);
				// CPU 점유중인 Thread를 Block State로 보냄.(100msec동안)
		  }catch(InterruptedException e ) {
				System.out.println(e.toString());
		   }
		System.out.println(Thread.currentThread().getName() + " : " + cnt++);
	 	if(cnt>=10) break;
    	}
	}
}
