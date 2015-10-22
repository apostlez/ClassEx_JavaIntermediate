package edu.jaen.java.ref.xml;

public class RefValue {
	private String name, value, type;
	public RefValue(){}
	public RefValue(String name, String value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("tag name:");
		sb.append(name);
		sb.append(" value:");
		sb.append(value);
		sb.append(" type:");
		sb.append(type);
		return sb.toString();
	}
}
