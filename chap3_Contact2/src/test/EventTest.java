package test;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EventTest {

	public static void main(String[] args) {
		Frame f = new Frame("EventTest");
		f.setSize(300, 300);
		f.setVisible(true);
		//Anonymous Nested class -> EventTest.1.class
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);				
			}
		});
	}
	// Member Nested class
	//static class WindowHandler implements WindowListener {
	//static WindowListener wHandler = new WindowListener() {}
}
