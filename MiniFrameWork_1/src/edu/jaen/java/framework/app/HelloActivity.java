package edu.jaen.java.framework.app;

import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.jaen.java.framework.component.Activity;
public class HelloActivity extends Activity {
	// onCreate() 를 Override 하고
	// setContentView()를 이용하여 화면을 로딩하고
	// findViewById()를 이용하여 UI Component를 
	// 생성한 후 클릭 이벤트를 처리한 후 다른 Activity를
	// startActivity() 이용하여 호출한 
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
