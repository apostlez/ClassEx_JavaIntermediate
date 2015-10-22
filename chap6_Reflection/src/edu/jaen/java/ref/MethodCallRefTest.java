package edu.jaen.java.ref;
import java.lang.reflect.Method;
public class MethodCallRefTest {
 public static void main(String[] args) {
  Customer cust = new Customer("mds", 10, "±¸·Î");
  System.out.println(cust);
  try {
	Class c =cust.getClass();
	Method[] m = c.getDeclaredMethods();
	for (Method method : m) {
		System.out.println(method.getName());
		if(method.getName().startsWith("setN")) method.invoke(cust, "kdg");
		if(method.getName().startsWith("g")) method.invoke(cust, null);
	}
	System.out.println(cust);
  } catch (Exception e) {
	e.printStackTrace();
  }
 }
}
