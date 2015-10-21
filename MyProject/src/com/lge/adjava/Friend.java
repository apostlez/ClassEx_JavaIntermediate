package com.lge.adjava;

public class Friend implements Comparable<Friend> {
	private String name;
	private String phone;
	private String email;
	
	public Friend() {
		super();
	}

	public Friend(String name, String phone, String email) {
		super();
		setName(name);
		setPhone(phone);
		setEmail(email);
	}

	public Friend(String name, String phone) {
		this(name, phone, "");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "name:" + name + ":" + "\nphone:" + phone + "\nemail:" + email;
		
	}
	
	@Override
	public int hashCode() {
		// java guide said the hashcode should use exclusive or operation
		// because it can be know certain objects has same data or not 
		return name.hashCode()^phone.hashCode()^email.hashCode();
		
	}

	@Override 
	public boolean equals(Object obj) {
		// compare contents of object ( this, parameter obj)
		if(obj != null && obj instanceof Friend) {
			Friend f = (Friend) obj;
			if(this.getName().equals(f.getName())
					&& this.getPhone().equals(f.getPhone())
					&& this.getEmail().equals(f.getEmail())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * compare with param o
	 * @param o
	 * @return int -,0,+
	 */
	@Override
	public int compareTo(Friend o) {
		if(o != null) {
			return this.getName().compareTo(o.getName());
		}
		return 0;
	}
	
	
}
