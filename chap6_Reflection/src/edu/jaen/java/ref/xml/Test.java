package edu.jaen.java.ref.xml;

import edu.jaen.java.ref.Customer;
import edu.jaen.java.ref.Phone;

public class Test {
	public static void main(String[] args) {
		try{
			System.out.println(NewInstanceSAX.getInstance("Customer.xml", new Customer()));
			System.out.println(NewInstanceSAX.getInstance("PhoneList.xml", new Phone()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
