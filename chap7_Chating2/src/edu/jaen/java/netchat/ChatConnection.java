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
			// 1. Socket ����
			s = new Socket(ip, port);

			showContent("�������� ���� !!\n");
			// 2. I/O stream ����
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());

			showContent("��Ʈ�� ���� ���� !!\n");
			// Thread �����...
			ChatClientThread t = new ChatClientThread(ois);
			t.start();

			showContent("������ ���� ���� !!\n");
			//tf.requestFocus();
		} catch (Exception e) {
			showContent("������ ����������,IP��port�� �´��� Ȯ�� �ٶ��ϴ�.");
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
				showContent("readObject()�� �����߻� : " + e.getMessage() + "\n");
			}
		}
	}// Inner
}
