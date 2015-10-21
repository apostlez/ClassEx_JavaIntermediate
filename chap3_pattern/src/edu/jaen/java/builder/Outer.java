package edu.jaen.java.builder;

public class Outer {
	int age;
	String name;
	String addr;
	float tall;
	boolean gender;
	
	
	public static class Builder{
		Outer out;
		public Builder(){
			out = new Outer();
		}
		public Builder setAge(int age){
			out.age = age;
			return this;
		}
		public Builder setName(String name){
			out.name = name;
			return this;
		}
		public Builder setAddr(String addr){
			out.addr = addr;
			return this;
		}
		public Builder setTall(float tall){
			out.tall = tall;
			return this;
		}
		public Builder setGender(boolean gender){
			out.gender = gender;
			return this;
		}
		public void show(){
			System.out.println(out.toString());
		}
	}

	@Override
	public String toString() {
		return "Outer [age=" + age + ", name=" + name + ", addr=" + addr
				+ ", tall=" + tall + ", gender=" + gender + "]";
	}
	

}
