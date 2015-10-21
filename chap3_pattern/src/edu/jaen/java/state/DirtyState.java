package edu.jaen.java.state;
public class DirtyState implements State { 
   private DirtyState() {}
   private static DirtyState dirty ;
 
   public static DirtyState getInstance(){
		if(dirty == null){
				dirty = new DirtyState();
		}
		return dirty;
   }
   public void open()    {
		System.out.println("������ ������ �ֽ��ϴ� ���Ͽ� �����ϰ� �ϰڽ��ϴ�...");
		save();
   }
   public void close()    {
    		System.out.println("������ ������ �ֽ��ϴ�.  �����ϰ� �����ϰڽ��ϴ�");
			save();
   }
   public void save()    {
        		System.out.println("���系���� �����մϴ�");
				Document.setState(CleanState.getInstance());
				
   }
   public void edit(String text)   {
   }
}
