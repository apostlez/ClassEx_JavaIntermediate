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
			System.out.println("버스를 타고 출근을 했습니다 10분 걸렸습니다");
	}
}

//product 2
class Bycle extends Trans{
	public void go(){
			System.out.println("자전거를 타고 출근을 했습니다 30분 걸렸습니다");
	}
}
//product 3
class Taxi extends Trans{
	public void go(){
			System.out.println("넘 늦어서 택시를 타고 출근을 했습니다.");
	}
}


//run...
public class FactoryTest{
	public static void main(String[] args){
		
		Creator rc = new RealCreator();  //Factory 생성
		Trans tc =rc.create(args[0]);    //product 생성
		tc.go();						 //product 사용	
	}
}
