package edu.jaen.java.builder;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Outer.Builder()
			.setAddr("seoul")
			.setAge(21)
			.setGender(true)
			.setName("kim")
			.setTall(180.1f)
			.show();
		
	}

}
