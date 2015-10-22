package edu.jaen.java.ref;

public class Customer {
	private String name;
	private int age;
	private String address;
	public Customer(){}
	public Customer(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("이름:");
		sb.append(name);
		sb.append(" 나이:");
		sb.append(age);
		sb.append(" 주소:");
		sb.append(address);
		return sb.toString(); 
	}
	
	public boolean equals(Object o){
		if (o!=null && o instanceof Customer) {
			Customer cust = (Customer) o;
			if(name.equals(cust.name)) return true;
		}
		return false;
	}
	public int hashCode(){
		if(name!=null) return name.hashCode() ^ age;
		else return super.hashCode();
	}
}
