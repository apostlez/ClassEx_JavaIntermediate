package edu.jaen.java.framework.app;

import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.jaen.java.framework.component.Activity;
public class HelloActivity extends Activity {
	// onCreate() �� Override �ϰ�
	// setContentView()�� �̿��Ͽ� ȭ���� �ε��ϰ�
	// findViewById()�� �̿��Ͽ� UI Component�� 
	// ������ �� Ŭ�� �̺�Ʈ�� ó���� �� �ٸ� Activity��
	// startActivity() �̿��Ͽ� ȣ���� 
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setContentView("layout");
		Button b = (Button) findViewById("btn1");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startActivity("HelloActivity2");
			}
		});
		Button b2 = (Button) findViewById("btn2");
		final Label t1 = (Label) findViewById("t1");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.setText("Change....");
			}
		});
	}
}
