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
	   text="  ## 문서 ## ";  
	   System.out.println("새로운 문서가 open되었습니다.");

   }
   public static void setState(State state)  {
	   theState=state;    
   }
   public void save() {
		theState.save();
   }
   public void edit(String text)  {
		this.text+=text;
		System.out.println("문서의 내용이 수정되었습니다 :"+this.text);
		theState.edit(text);
   }
   public void close(){
		theState.close();
	   System.out.println(text+" 문서가 close 되었습니다.");
	   text="";

}
}

