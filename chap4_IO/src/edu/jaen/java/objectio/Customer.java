package edu.jaen.java.objectio;

// Externalizable : custum Serializable
public class Customer  implements java.io.Serializable
{
	String  name;
	String  address;
	// transient: ������ ������
	transient int	    age;

	public String toString(){
		return "�̸� : "+name+"\t�ּ� : "+address+"\t���� : "+age;
	}
}


