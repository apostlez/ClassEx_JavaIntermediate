package edu.jaen.java.thread.syncwait;
class MainData {
	int data;
	 public void increase(String name){	
		System.out.print(name + "가 " + data);
		pause(500);
		data++;
		//this.notify();
		System.out.println("를 " + data + "로 증가시킴");
	}
	 public void decrease(String name){
		//if(data <= 0)this.wait();
		System.out.print(name + "가 " + data);
		pause(100);
		data--;
		System.out.println("를 " + data + "로 감소시킴");
	}
	public void pause(long time){
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){}
	}
}