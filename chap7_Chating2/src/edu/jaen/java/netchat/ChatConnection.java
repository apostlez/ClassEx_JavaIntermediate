package edu.jaen.java.netchat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatConnection {
	private ChatClient cc = null;
	private Socket s = null;
	private String ip;
	private int port;
	private String name;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public ChatConnection(ChatClient cc) {
		this.cc = cc;		
	}

	public void go(String ip, int port, String name) {
		this.ip = ip;
		this.port = port;
		this.name = name;

		try {
			// 1. Socket 생성
			s = new Socket(ip, port);

			showContent("서버접속 성공 !!\n");
			// 2. I/O stream 생성
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());

			showContent("스트림 생성 성공 !!\n");
			// Thread 만들기...
			ChatClientThread t = new ChatClientThread(ois);
			t.start();

			showContent("쓰레드 생성 성공 !!\n");
			//tf.requestFocus();
		} catch (Exception e) {
			showContent("서버가 시작중인지,IP와port가 맞는지 확인 바랍니다.");
		}
	}
	
	public void sendMessage(String message) {
		try {
			String msg = "[" + name + "]" + message;
			oos.writeObject(msg);
			System.out.println("send message:" + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showContent(String message) {
		cc.showMessages(message);
	}
	
	class ChatClientThread extends Thread {
		private ObjectInputStream ois = null;

		public ChatClientThread(ObjectInputStream ois) {
			this.ois = ois;
		}

		public void run() {
			try {
				while (true) {
					String msg = (String) ois.readObject();
					showContent(msg + "\n");
				}
			} catch (Exception e) {
				showContent("readObject()시 오류발생 : " + e.getMessage() + "\n");
			}
		}
	}// Inner
}
