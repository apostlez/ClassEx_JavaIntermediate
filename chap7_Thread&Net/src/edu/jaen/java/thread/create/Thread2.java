package edu.jaen.java.thread.create;
public class Thread2 {
	public static void main(String args[]) {
		System.out.println("Main start.."+Thread.currentThread().getName());
		MyThread2 r1 = new MyThread2("One");
		MyThread2 r2 = new MyThread2("Two");
		MyThread2 r3 = new MyThread2("Three");
		r1.start();
		r2.start();
		r3.start();
		System.out.println("Main end.."+Thread.currentThread().getName());
	}
}
class MyThread2 extends Thread {
	int i;
	public MyThread2() {}
	public MyThread2(String name)
	{ super(name); }
	public void run() {
		while(++i<11){
			System.out.println(currentThread().getName()+" : "+i);
			/*try{
				sleep(2000);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			*/
		}
	}
}

