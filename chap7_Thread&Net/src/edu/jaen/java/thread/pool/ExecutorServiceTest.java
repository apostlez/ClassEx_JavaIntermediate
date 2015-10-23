package edu.jaen.java.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class MyRunnable implements Runnable{
	public void run(){
		String name = Thread.currentThread().getName();
		for (int i = 0; i < 5; i++) {
			//수행시켜주는 쓰레드 이름 확인 
			System.out.println(name+":"+i);
		}
	}
}
public class ExecutorServiceTest {
	public static void main(String[] args) {
		//최대 사용할 Thread의 개수를 10 개로 지정
		ExecutorService   pool =Executors.newFixedThreadPool(10);
		MyRunnable  command = new MyRunnable();
		
		//command를 10번 수행해 보기 
		for (int i = 0; i < 10; i++) {
			pool.execute(command);
		}
		try{Thread.sleep(10000);}catch(Exception e){}
		
		System.out.println("---------------");
		
		//기존의 쓰레드를 재사용하는지 확인
		for (int i = 0; i < 5; i++) {
			pool.execute(command);
		}
	}
}
