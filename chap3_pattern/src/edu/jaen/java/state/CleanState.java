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
	   System.out.println("������ �����մϴ�.");
   }
   public void close()  {
	   System.out.println("������ �����մϴ�.");
   }
   public void save()  {
	   System.out.println("�̹� ���� �Ǿ����Ƿ� �������� �ʽ��ϴ�.");
   }
   public void edit(String text)  {
	   System.out.println("������ �����Ǿ����ϴ�..");
		  Document.setState(DirtyState.getInstance());
   }
}
