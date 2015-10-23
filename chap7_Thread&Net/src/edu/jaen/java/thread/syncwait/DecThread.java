package edu.jaen.java.thread.syncwait;
class DecThread extends Thread {
  MainData md;
  public DecThread(MainData md,String n){ 
	super(n);
  	this.md = md;
 }
 
  public void run(){  	
  	for(int i=0;i<10;i++){
  		try{ 
  			sleep((int)(Math.random()*5000));
  		} catch (Exception e) {
  		}
  		md.decrease(getName());
  	}
	}
}
	