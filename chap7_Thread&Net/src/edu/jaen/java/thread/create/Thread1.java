package edu.jaen.java.thread.create;
public class Thread1 {
	public static void main(String args[]) {
		System.out.println("main()...1...");
		MyThread a = new MyThread();
		a.start();//->run() call
		System.out.println("main()...2...");
		//try{
		//	Thread.sleep(30);
		//}catch(InterruptedException ie){}
		System.out.println("main()...3...");
	}
}
class MyThread extends Thread {
	int i;
	public void run() {
	  while(true) {
			System.out.println(Thread.currentThread().getName() + " : "+i++);
		try{
			Thread.sleep(30);
		}catch(InterruptedException ie){}
			if(i>=10)
			break;
		}
	}
}
