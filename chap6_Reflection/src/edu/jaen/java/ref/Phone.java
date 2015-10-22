package edu.jaen.java.ref;

public class Phone {
	private String name;
	private int hotkey;
	private String no;
	public Phone(){}
	
	public Phone(String name, int hotkey, String no) {
		super();
		this.name = name;
		this.hotkey = hotkey;
		this.no = no;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHotkey() {
		return hotkey;
	}

	public void setHotkey(int hotkey) {
		this.hotkey = hotkey;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("이름:");
		sb.append(name);
		sb.append(" 전화번호:");
		sb.append(no);
		sb.append(" 단축번호:");
		sb.append(hotkey);
		return sb.toString(); 
	}
	
	
}
