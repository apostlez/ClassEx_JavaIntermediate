package edu.jaen.java.ref;
import java.lang.reflect.Method;
public class CreateObjectRefTest {
 public static void main(String[] args) {
	 String cName = "edu.jaen.java.ref.Customer";
/*  if(args.length<1){
	System.out.println("생성할 클래스 이름을 패키지 포함해서 입력하시오");
	System.out.println("usage :  java edu.jaen.java.ref.ReflectionTest2   edu.jaen.java.ref.Customer ");
	System.exit(0);
  }*/
  try {
	Class c = Class.forName(cName);
	Object cust = c.newInstance();
	Method[] m = c.getDeclaredMethods();
	Method[] m2 = c.getMethods();
	for (Method method : m) {
		System.out.println(method.getName());
		if(method.getName().startsWith("setN")) method.invoke(cust, "kdg");
		if(method.getName().startsWith("g")) method.invoke(cust, null);
	}
/*	System.out.println("=getMethods================================");
	for (Method method : m2) {
		System.out.println(method.getName());
		//if(method.getName().startsWith("setN")) method.invoke(cust, "kdg");
		//if(method.getName().startsWith("g")) method.invoke(cust, null);
	}*/
	System.out.println(cust);
  } catch (Exception e) {
	e.printStackTrace();
  }
 }
}
