package com.lge.adjava;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Friend f = new Friend();
		f.setName("Hong Gildong");
		f.setPhone("010-1234-5678");
		f.setEmail("gildong.hong@hwoalbindang.org");
		System.out.println(f);

		Friend f2 = new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org");
		if(f2.equals(f)) {
			System.out.println("true");
		}
		
		Parent p = new Test().new Child();
		p.print();
	}
	
	class Parent {
		int a = 1;
		
		{
			System.out.println("Parent");
		}
		
		public void print() {
			System.out.println("print parent");
			System.out.println(a);
		}
	};
	
	class Child extends Parent {
		int a = 2;
		
		{
			System.out.println("child");
		}
		
		public void print() {
			System.out.println("print child");
			System.out.println(a);
		}
	};

}
