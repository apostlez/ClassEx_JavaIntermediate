package edu.jaen.java.netchat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

	private ArrayList<User> users = new ArrayList<User>();

	private int port = 5500;

	public void go() {

		try {
			// 1. ServerSocket ����
			ServerSocket ss = new ServerSocket(port);
			System.out.println("ServerSocket ���� ����. port=" + port);
			while (true) {
				// 2. Socket����: Client ���ӽ�
				Socket s = ss.accept();

				// 3. ���� Client�� I/O Stream
		        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		        ObjectInputStream in = new ObjectInputStream(s.getInputStream());

				// 4. Client ���� ����
		        users.add(new User(s, in, out));

				// 5. ������ Client�� ���� �Է��� ����ϴ� Thread����
		        new ChatServerThread(in).start();
			}
		} catch (Exception ee) {
			System.out.println("�����߻� ... : " + ee.getMessage());
		}
	}

	public void broadcast(String msg) {
		for(User u : users) {
			try {
				u.getObjectOutputStream().writeObject(msg);
			} catch (IOException e) {
				removeClient(u.getObjectInputStream());
				System.out.println("connection closed.");
			}
		}
	}

	public void removeClient(ObjectInputStream ois) {
		for (int i = 0; i < users.size(); i++) {
			User u = (User) users.get(i);
			ObjectInputStream us = u.getObjectInputStream();
			if (ois == us) {
				try {
					us.close();
					u.getObjectOutputStream().close();
					u.getSocket().close();
				} catch (Exception e) {
				}
				users.remove(u);
				System.out.println("1 client exited !!");
			}
		}
	}

	public static void main(String[] args) {
		// ChatServer cs = new ChatServer(port);
		// cs.go();
		new ChatServer().go();
	}

	class ChatServerThread extends Thread {
		private ObjectInputStream ois;

		public ChatServerThread(ObjectInputStream ois) {
			this.ois = ois;
		}

		public void run() {
			String msg = null;
			System.out.println("thread started");
			while(true) {
				try {
					msg = (String)ois.readObject();
					System.out.println("message recieved:" + msg);
				} catch (IOException e) {
					removeClient(ois);
					System.out.println("connection closed.");
					break;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				broadcast(msg);
			}
			System.out.println("exit thread");
		}
	}
}

class User {
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public User(Socket s, ObjectInputStream ois, ObjectOutputStream oos) {
		this.s = s;
		this.ois = ois;
		this.oos = oos;
	}

	public Socket getSocket() {
		return s;
	}

	public ObjectInputStream getObjectInputStream() {
		return ois;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return oos;
	}
}
