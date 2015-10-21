package edu.jaen.java.singleton;
public class Singleton
{
	private static Singleton singleton = new Singleton();

	private Singleton() {
		System.out.println("�ν��Ͻ��� �����߽��ϴ�.");
	}
	public static Singleton getInstance() {
		return singleton;
	}
}
