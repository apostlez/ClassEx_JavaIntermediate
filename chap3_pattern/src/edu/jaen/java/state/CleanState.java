package edu.jaen.java.state;
public class CleanState implements State {
   private static CleanState clean ;
   private CleanState()  {  }

   public static  CleanState getInstance(){
		if(clean == null){
				clean = new CleanState();
		}
		return clean;
   }
   public void open()  {
	   System.out.println("문서를 오픈합니다.");
   }
   public void close()  {
	   System.out.println("문서를 종료합니다.");
   }
   public void save()  {
	   System.out.println("이미 저장 되었으므로 저장하지 않습니다.");
   }
   public void edit(String text)  {
	   System.out.println("문서가 수정되었습니다..");
		  Document.setState(DirtyState.getInstance());
   }
}
