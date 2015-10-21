package edu.jaen.java;

import java.util.*;
import java.io.*;
/** 고객의 데이타를 관리, 저장하는 클래스 */
public class  CustomerDAO{
	private ArrayList<Customer> list;
	public CustomerDAO(){
		list=new ArrayList<Customer>(); 
		open();
	}
/** 파일로 부터 자료 읽어서 메모리(ArrayList)에 저장하기*/
	public void open(){
		ObjectInputStream ois=null;
		File file=new File("cust.dat");
		if (!file.exists()) { return; }
			
			
			
			
			//  구현해 보세요
			
			
			
		
	}
/** 종료하기 전에 메모리의 내용을 파일에 저장하기 */
	public void close(){

		
		//  구현해 보세요
		
		
		
		
	}

/** 전달된 고객의 정보를 추가한다.*/
	public void addCust( String name,String phone,int hotKey){
		Customer cu=new Customer( name,phone,hotKey);
		list.add(cu);
	}
/** 고객의 모든 정보를 리턴한다.*/
	public ArrayList<Customer> allCust(){
		return list;
	}
	/** 이름 검색 메소드*/
	public Customer search(String name){
		for(Customer cu: list){
			if(name.equals(cu.getName()))
				return cu;
		}
		return null;
	}
	/** 단축키 검색 메소드*/
	public Customer search(int hotKey){
		for(Customer cu: list){
			if(hotKey==(cu.getHotKey()))
				return cu;
		}
		return null;
	}
	/** 이름 검색, 제거 메소드 */
	public void delete(String name){
		Customer cu=search(name);
		list.remove(cu);
	}
	/** 단축키를 검색, 제거 메소드*/
	public void delete(int hotKey){
		Customer cu=search(hotKey);
		list.remove(cu);
	}
	/** 이름 검색하여 번호, 주소를 수정한다. 동명이인 없음 가정*/
	public void updateCust( String name,String phone,int hotKey){
	 Customer cu=search(name);
	 cu.setPhone(phone);
	 cu.setHotKey(hotKey);
	}
}
