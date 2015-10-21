package com.lge.adjava;

public class Family extends Friend {
	private String birth;
	
	public Family() {
		super();
	}
	
	public Family(String name, String phone, String email, String birth) {
		super(name, phone, email);
		this.birth = birth;
	}
	
/*	public String toString() {
		return super.toString() + "\nbirth" + this.birth;
	}
*/
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Family [birth=");
		builder.append(birth);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
