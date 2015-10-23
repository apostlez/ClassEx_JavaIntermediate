package edu.jaen.java.thread.control;
public class PriThread extends Thread {
	public PriThread(String name){
		super(name);	
	}
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println(getName() + "가 출력:"+i);
			//try{
			//	sleep(1);
			//}catch(InterruptedException e){}
		}
	}
	public static void main(String args[]){
		PriThread p1, p2, p3;
		
		p1 = new PriThread("max");
		p2 = new PriThread("norm");
		p3 = new PriThread("min");
		
		p1.setPriority(p1.MAX_PRIORITY);  //10
		p2.setPriority(p1.NORM_PRIORITY); //5
		p3.setPriority(p1.MIN_PRIORITY);  //1
		System.out.println(p1.getName() + " 우선순위 : "+p1.getPriority());
		System.out.println(p2.getName() + " 우선순위 : "+p2.getPriority());
		System.out.println(p3.getName() + " 우선순위 : "+p3.getPriority());

		p3.start();
		p2.start();
		p1.start();
   }
}