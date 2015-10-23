package edu.jaen.java.net.nested;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{

	private ArrayList<User> users= new ArrayList<User>();

	private int port=5500;

	public void go() {
		
		try{
		//1. ServerSocket 생성
		ServerSocket ss = new ServerSocket( port );
		System.out.println("ServerSocket 생성 성공. port="+port);
		while( true ) {
			//2. Socket생성: Client 접속시
			Socket s = ss.accept();
			System.out.println("1 client connected !!");
			//3. 각각 Client에 I/O Stream
			ObjectInputStream ois = new ObjectInputStream( s.getInputStream() );
			ObjectOutputStream oos = new ObjectOutputStream( s.getOutputStream() );
			//4. Client 정보 저장
			users.add( new User(s,ois,oos));
			//5. 각각의 Client로 부터 입력을 담당하는 Thread생성
			new ChatServerThread( ois ).start();

		}
		}catch(Exception ee ) {
			System.out.println("오류발생 ... : "+ee.getMessage() );
		}
	}
   
	public void broadcast( String msg ) {
		for( User  u: users){
			ObjectOutputStream oos = u.getObjectOutputStream();
			try{
				oos.writeObject( msg );
			}catch(Exception e ) {
				System.out.println("오류발생 : "+e.getMessage() );
			}
		}
	}

	public void removeClient( ObjectInputStream ois ) {
		for( User u: users){
			ObjectInputStream us = u.getObjectInputStream();
			if(ois == us){
				try{	
					us.close();
					u.getObjectOutputStream().close();
					u.getSocket().close();
				}catch( Exception e ) {}
				users.remove(u); 
				System.out.println("1 client exited !!");
				break;
			}
		}
	}

	public static void main(String[] args) 	{
			// ChatServer cs = new ChatServer(port);
			// cs.go();
			new ChatServer().go();
	}

class ChatServerThread extends Thread {
	private ObjectInputStream ois ;

	public ChatServerThread(  ObjectInputStream ois){
		this.ois=ois;
	}

	public void run(){
		try{
			while( true  ) {
				String msg = (String)ois.readObject();
				broadcast( msg );
			}
		}catch( Exception ioe ) {
			removeClient( ois );
		}
	}
}
}


class User{
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public User(Socket s, ObjectInputStream ois,ObjectOutputStream oos ){
		this.s = s;
		this.ois=ois;
		this.oos=oos;
	}
	public Socket getSocket(){ return s; }
	public ObjectInputStream getObjectInputStream(){ return ois; }
	public ObjectOutputStream getObjectOutputStream(){ return oos; }
}

