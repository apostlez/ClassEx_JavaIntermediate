package edu.jaen.java.net.nested;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class  ChatClient3 extends Frame implements ActionListener{

	private TextArea ta=new TextArea();
	private TextField tf = new TextField();
	private Button b1=new Button("Send");
	private Button b2=new Button("Exit");
	private Panel p=new Panel();
	private Socket s =null;
	private ObjectOutputStream oos=null;
	private ObjectInputStream ois=null;

	private String ip;
	private int port;
	private String name;

	public ChatClient3(){
		createGUI();
	}
	//GUI 생성하고 Event 등록,처리
	public void createGUI(){
		p.add(b1);
		p.add(b2);
		add( ta,"West" );
		add(p,"Center");
		
		add( tf, "South");
		
		tf.addActionListener( this );//Event 등록
		setBounds( 200,200,500,400 );
		setVisible( true );
		b1.addActionListener(this);//Event 등록
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
					System.exit(0);
		    }
		});
		
		addWindowListener( new WindowAdapter() {
				public void windowClosing( WindowEvent we ) {
					System.exit(0);
				}
		});
		
	}
	public  void go( String ip, int port, String name){
		this.ip = ip;
		this.port=port;
		this.name=name;

		try {
			//1. Socket 생성
			s = new Socket( ip, port );
			ta.append("서버접속 성공 !!\n");
			//2. I/O stream 생성
			oos = new ObjectOutputStream( s.getOutputStream() );
			ois = new ObjectInputStream( s.getInputStream() );
			ta.append("스트림 생성 성공 !!\n");
		//  Thread 만들기...
			//new ChatClientThread(ois ).start();
			new Thread( ){
				public void run(){
					try{
					while( true ) {
						String msg = (String) ois.readObject();
						ta.append( msg +"\n" );
					}
					}catch(Exception e ) {
						ta.append("readObject()시 오류발생 : "+e.getMessage()+"\n");
					}
				}				
			}.start();
			ta.append("쓰레드 생성 성공 !!\n");
			tf.requestFocus();
		}catch(Exception e ){
			ta.append("서버가 시작중인지,IP와port가 맞는지 확인 바랍니다.");
		}
	}

	

	public void actionPerformed( ActionEvent ae ) {
		String msg = tf.getText();
		try{
			oos.writeObject( "["+name+"] "+msg );
		}catch( IOException ioe ) {
			System.out.println("메세지 전송중 오류발생 : "+ioe.getMessage());
			System.exit(0);
		}
		tf.setText("");
	}
	public static void main(String[] args) 	{
		String ip = "127.0.0.1";
		int port = 5500;
		String name = "userID";

		//ChatClient cc=new ChatClient();
		//cc.go( ip,port,name );
		new ChatClient3().go( ip,port,name );
	}

}//outer










