package edu.jaen.java.netchat;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame implements ActionListener {

	private TextArea ta = new TextArea();
	private TextField tf = new TextField();
	private Button b1 = new Button("Send");
	private Button b2 = new Button("Exit");
	private Panel p = new Panel();
	private ChatConnection cc = null;

	public ChatClient() {
		createGUI();
	}
	
	public ChatClient(String ip, int port, String name) {
		this();
		cc = new ChatConnection(this);
		cc.go(ip, port, name);
		tf.requestFocus();
	}

	// GUI 생성하고 Event 등록,처리
	public void createGUI() {
		p.add(b1);
		p.add(b2);
		add(ta, "West");
		add(p, "Center");

		add(tf, "South");

		tf.addActionListener(this);// Event 등록
		setBounds(200, 200, 500, 400);
		setVisible(true);
		b1.addActionListener(this);// Event 등록
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	public void actionPerformed(ActionEvent ae) {
		cc.sendMessage(tf.getText().trim());
		tf.setText("");
	}
	
	public void showMessages(String message) {
		ta.append(message);
	}

	public static void main(String[] args) {

		String ip = "127.0.0.1";
		//String ip = "192.168.10.57";
		int port = 5500;
		String name = "Yunkwan.kim";//"userID";

		// ChatClient cc=new ChatClient();
		// cc.go( ip,port,name );
		new ChatClient(ip, port, name);
	}

}// outer

