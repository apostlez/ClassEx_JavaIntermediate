package edu.jaen.java.thread.create;
import java.awt.*;
import java.awt.event.*;
class StringThread extends Frame implements Runnable{
	int y=300;
   public StringThread () {
	   createGUI();
	  addEvent();
   }
   public void createGUI(){
	   setSize(300, 300);
       setVisible(true);
   }
   public void paint(Graphics  g) {
       g.setColor(Color.red);
       g.fillOval(50, 50, 50, 50);
       g.setColor(Color.blue);
       g.drawString("How about my appearance",50, y);
   }
   void addEvent() {
	addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent we){
			System.exit(0);
		}
	});
    addMouseListener( new MouseAdapter() {
        public void mousePressed(MouseEvent  e) {
           Graphics  g = getGraphics(); 
           g.setColor(Color.blue);  
           g.fillOval(e.getX(),e.getY(), 50, 50);
        }
      }
    );
  }
   public static void main(String  arg[]) {
        StringThread st=new  StringThread();
		Thread th=new Thread(st);
		th.start();
   }
   public void run(){
	   while(y>0){
		   if(y==1)
			   y=300;
		   y--;
		   repaint();
		   try{
			   Thread.sleep(10);
		   }catch(InterruptedException ie){
			   ie.printStackTrace();
		   }//end catch
	   }//end while
   }//end run
}