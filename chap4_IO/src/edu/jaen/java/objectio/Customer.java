package edu.jaen.java.objectio;

// Externalizable : custum Serializable
public class Customer  implements java.io.Serializable
{
	String  name;
	String  address;
	// transient: 버리는 데이터
	transient int	    age;

	public String toString(){
		return "이름 : "+name+"\t주소 : "+address+"\t나이 : "+age;
	}
}


