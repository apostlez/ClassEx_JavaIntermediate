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
		System.out.println("수정된 사항이 있습니다 파일에 저장하고 하겠습니다...");
		save();
   }
   public void close()    {
    		System.out.println("수정된 사항이 있습니다.  저장하고 종료하겠습니다");
			save();
   }
   public void save()    {
        		System.out.println("현재내용을 저장합니다");
				Document.setState(CleanState.getInstance());
				
   }
   public void edit(String text)   {
   }
}
