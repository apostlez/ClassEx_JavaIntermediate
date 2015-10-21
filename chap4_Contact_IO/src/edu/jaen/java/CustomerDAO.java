package edu.jaen.java;

import java.util.*;

import edu.jaen.java.Customer;

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
			
		try {
			ois = new ObjectInputStream (new FileInputStream (file));
			//list.add((Customer) ois.readObject());
			list.addAll((ArrayList<Customer>) ois.readObject());
		} catch (EOFException e) {
			System.out.println("eof");
	    } catch (IOException e) {
	    	e.printStackTrace ();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
/** �����ϱ� ���� �޸��� ������ ���Ͽ� �����ϱ� */
	public void close(){
		if(list.size() == 0) {
			return;
		}
		ObjectOutputStream s = null;
		try {
			s = new ObjectOutputStream (new FileOutputStream ("cust.dat"));
			s.writeObject(list);
/*			for(Customer c : list) {
				s.writeObject(c);
	        }
*/			System.out.println(" ������ �����Ǿ����ϴ�");
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
