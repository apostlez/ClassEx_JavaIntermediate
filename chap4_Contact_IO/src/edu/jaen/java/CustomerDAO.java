package edu.jaen.java;

import java.util.*;
import java.io.*;
/** ���� ����Ÿ�� ����, �����ϴ� Ŭ���� */
public class  CustomerDAO{
	private ArrayList<Customer> list;
	public CustomerDAO(){
		list=new ArrayList<Customer>(); 
		open();
	}
/** ���Ϸ� ���� �ڷ� �о �޸�(ArrayList)�� �����ϱ�*/
	public void open(){
		ObjectInputStream ois=null;
		File file=new File("cust.dat");
		if (!file.exists()) { return; }
			
			
			
			
			//  ������ ������
			
			
			
		
	}
/** �����ϱ� ���� �޸��� ������ ���Ͽ� �����ϱ� */
	public void close(){

		
		//  ������ ������
		
		
		
		
	}

/** ���޵� ���� ������ �߰��Ѵ�.*/
	public void addCust( String name,String phone,int hotKey){
		Customer cu=new Customer( name,phone,hotKey);
		list.add(cu);
	}
/** ���� ��� ������ �����Ѵ�.*/
	public ArrayList<Customer> allCust(){
		return list;
	}
	/** �̸� �˻� �޼ҵ�*/
	public Customer search(String name){
		for(Customer cu: list){
			if(name.equals(cu.getName()))
				return cu;
		}
		return null;
	}
	/** ����Ű �˻� �޼ҵ�*/
	public Customer search(int hotKey){
		for(Customer cu: list){
			if(hotKey==(cu.getHotKey()))
				return cu;
		}
		return null;
	}
	/** �̸� �˻�, ���� �޼ҵ� */
	public void delete(String name){
		Customer cu=search(name);
		list.remove(cu);
	}
	/** ����Ű�� �˻�, ���� �޼ҵ�*/
	public void delete(int hotKey){
		Customer cu=search(hotKey);
		list.remove(cu);
	}
	/** �̸� �˻��Ͽ� ��ȣ, �ּҸ� �����Ѵ�. �������� ���� ����*/
	public void updateCust( String name,String phone,int hotKey){
	 Customer cu=search(name);
	 cu.setPhone(phone);
	 cu.setHotKey(hotKey);
	}
}
