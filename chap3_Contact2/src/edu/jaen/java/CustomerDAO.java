package edu.jaen.java;

import java.util.*;
import java.io.*;
/** 고객의 데이타를 관리, 저장하는 클래스 */
public class  CustomerDAO{
	private ArrayList<Customer> list;
	public CustomerDAO(){
		list=new ArrayList<Customer>(); 
	}

/** 전달된 고객의 정보를 추가한다.*/
	public void addCust( String name,String phone,int hotKey){
		if(name != null && phone != null && hotKey != 0) {
			list.add(new Customer(name, phone, hotKey));
		}
	}
/** 고객의 모든 정보를 리턴한다.*/
	public ArrayList<Customer> allCust(){
		return list;
	}
	/** 이름 검색 메소드*/
	public Customer search(String name){
		for(Customer c : list) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	/** 단축키 검색 메소드*/
	public Customer search(int hotKey){
		for(Customer c : list) {
			if(c.getHotKey() == hotKey) {
				return c;
			}
		}
		return null;
	}
	/** 이름 검색, 제거 메소드 */
	public void delete(String name){
		Customer cu=search(name);
		if(cu != null) {
			list.remove(cu);
		}
	}
	/** 단축키를 검색, 제거 메소드*/
	public void delete(int hotKey){
		Customer cu=search(hotKey);
		if(cu != null) {
			list.remove(cu);
		}
	}
	/** 이름 검색하여 번호, 주소를 수정한다. 동명이인 없음 가정*/
	public void updateCust( String name,String phone,int hotKey){
		Customer cu=search(name);
		if(cu != null) {
			cu.setPhone(phone);
			cu.setHotKey(hotKey);
		}
	}
}
