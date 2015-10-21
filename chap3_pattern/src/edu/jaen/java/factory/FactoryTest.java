package edu.jaen.java.factory;
//Creator class
abstract class Creator{
		//Factory method
		public abstract Trans create(String status);
}

//Concrete Class
 class RealCreator extends Creator{
   //Factory method implements....
	public Trans create(String status){
			if(status.equals("normal")){
					return new Bycle();
			}else if(status.equals("busy")){
					return new Taxi();
			}else {
					return new Bus();
			}
	}
}

//Abstract Product
abstract class Trans {
	public abstract void go();

}

//Product 1
class Bus extends Trans{
	public void go(){
			System.out.println("������ Ÿ�� ����� �߽��ϴ� 10�� �ɷȽ��ϴ�");
	}
}

//product 2
class Bycle extends Trans{
	public void go(){
			System.out.println("�����Ÿ� Ÿ�� ����� �߽��ϴ� 30�� �ɷȽ��ϴ�");
	}
}
//product 3
class Taxi extends Trans{
	public void go(){
			System.out.println("�� �ʾ �ýø� Ÿ�� ����� �߽��ϴ�.");
	}
}


//run...
public class FactoryTest{
	public static void main(String[] args){
		
		Creator rc = new RealCreator();  //Factory ����
		Trans tc =rc.create(args[0]);    //product ����
		tc.go();						 //product ���	
	}
}
