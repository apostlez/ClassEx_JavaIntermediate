package edu.jaen.java.state;
public class Document {
	
   private String text = "";
   private static State theState=CleanState.getInstance();
   public void setText(String text){
	   this.text=text;
   }
   public String getText(){
	   return text;
   }
   public void open()  {
	   theState.open();
	   text="  ## ���� ## ";  
	   System.out.println("���ο� ������ open�Ǿ����ϴ�.");

   }
   public static void setState(State state)  {
	   theState=state;    
   }
   public void save() {
		theState.save();
   }
   public void edit(String text)  {
		this.text+=text;
		System.out.println("������ ������ �����Ǿ����ϴ� :"+this.text);
		theState.edit(text);
   }
   public void close(){
		theState.close();
	   System.out.println(text+" ������ close �Ǿ����ϴ�.");
	   text="";

}
}

