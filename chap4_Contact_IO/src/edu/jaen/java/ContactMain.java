package edu.jaen.java;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// AWT �� �̿��Ͽ� �ּҷϿ� ���/����/���� ����� ������ ����.

public class ContactMain implements ActionListener,ItemListener{
	Frame f=new Frame("Phone Book");
	Label custl=new Label("Phone Book",Label.CENTER);
	Label namel=new Label("��  ��",Label.CENTER);
	Label phonel=new Label("�ڵ���",Label.CENTER);
	Label hotKeyl=new Label("����Ű",Label.CENTER);
	Button insertb=new Button("INSERT");
	Button deleteb=new Button("DELETE");
	Button updateb=new Button("UPDATE");
	Button searchb=new Button("SEARCH");
	Button clearb=new Button("CLEAR");
	Button exitb=new Button("EXIT");
	List li=new List();
	TextField nametf=new TextField();
	TextField phonetf=new TextField();
	TextField hotKeytf=new TextField();
	Panel p1=new Panel();
	Panel p2=new Panel();
	Panel p2n=new Panel();
	Panel p2c=new Panel();
	Panel p2s=new Panel();
	CustomerDAO cl;
	MessageDialog md;
	public ContactMain(){
		cl=new CustomerDAO();
		md=new MessageDialog(f,"��  ��");//Dialog ����(owner,title)
		createGUI();
		addEvent();
		showList();
	}
/** GUI �� �����Ѵ�.*/
	public void createGUI(){
		f.setLayout(new GridLayout(2,1,5,5));
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());

		p1.add(custl,BorderLayout.NORTH);
		p1.add(li);

		p2n.add(insertb);
		p2n.add(deleteb);
		p2n.add(updateb);
		p2n.add(searchb);

		p2c.setLayout(new GridLayout(3,2,5,7));
		p2c.add(namel);
		p2c.add(nametf);
		p2c.add(phonel);
		p2c.add(phonetf);
		p2c.add(hotKeyl);
		p2c.add(hotKeytf);
		
		p2s.add(clearb);
		p2s.add(exitb);

		p2.add(p2n,"North");
		p2.add(p2c);
		p2.add(p2s,"South");

		f.add(p1);
		f.add(p2);
		
		f.setSize(350,350);
		f.setVisible(true);
	}
/** �̺�Ʈ�� ��� �Ǵ� ó���Ѵ�. */
	public void addEvent(){
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
							System.exit(0);
			} 
		});
		insertb.addActionListener(this);
		deleteb.addActionListener(this);
		updateb.addActionListener(this);
		searchb.addActionListener(this);
		clearb.addActionListener(this);
		exitb.addActionListener(this);
		li.addItemListener(this);	
	}
/** ��ư�� �����ų� Ŭ���Ǿ� ActionEvent�� �߻����� �� ����ȴ�. 
ActionListener �� actionPerformed method Overrinding*/
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==insertb){
				insertRecord();
		}else if(e.getSource()==deleteb){
				deleteRecord();
		}else if(e.getSource()==updateb){
				updateRecord();
		}else if(e.getSource()==searchb){
				searchRecord();
		}else if(e.getSource()==clearb){
				clearText();
		}else {
				cl.close();
				System.exit(0);
		}

	}
/** TextField�� ������ �����. */
	public void clearText(){
			phonetf.setText("");
			nametf.setText("");
			hotKeytf.setText("");
	}
/** Insert Button�� ������ �� ActionPerformed Method�� ���� ȣ��ȴ�.*/
	public void insertRecord(){
		//int<==String   
		//trim()...���ڿ��� ���� ����
		String phone=phonetf.getText().trim();
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(phone.equals("")||name.equals("")||hotKey.equals("")){
			md.show("����ִ� �׸��� �ֽ��ϴ�");
			return;
		}
		if(cl.search(Integer.parseInt(hotKey))!=null){
			md.show("�̹� �ִ� ����Ű�Դϴ�.");
			return;
		}
		cl.addCust(name,phone,Integer.parseInt(hotKey));
		showList();
		clearText();
	}
/** Delete Button�� ������ �� ActionPerformed Method�� ���� ȣ��ȴ�.*/
	public void deleteRecord(){		
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(name.equals("")||hotKey.equals("")){
			md.show("������ �߸� �Ǿ����ϴ�");
			return;
		}
		if(!name.equals("")){
			if(cl.search(name)==null){
				md.show("�������� �ʴ� �̸��Դϴ�.");
				return;
			}else{
				cl.delete(name);
			}
		}else{
			if(cl.search(Integer.parseInt(hotKey))==null){
				md.show("���� ���� �ʴ� ����Ű�Դϴ�.");
				return;
			}else{
				cl.delete(hotKey);
			}
		}
		showList();
		clearText();
	}
/** Update Button�� ������ �� ActionPerformed Method�� ���� ȣ��ȴ�.*/
	public void updateRecord(){
		String phone=phonetf.getText().trim();
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(phone.equals("")||name.equals("")||hotKey.equals("")){
			md.show("����ִ� �׸��� �ֽ��ϴ�");
			return;
		}
		if(cl.search(name)== null){
			md.show("�����ϴ� �̸��� �����ϴ�.");
			return;
		}
		cl.updateCust (name, phone,Integer.parseInt( hotKey));
		showList();
		clearText();
	}
/** Search Button�� ������ �� ActionPerformed Method�� ���� ȣ��ȴ�.*/
	public void searchRecord(){
		Customer c=null;
		String hotKey=hotKeytf.getText().trim();
		String name=nametf.getText().trim();
		if(!(name.equals(""))){
			c=cl.search(name);
		}else if(!(hotKey.equals(""))){
			c=cl.search(Integer.parseInt(hotKey));
		}else {
			//System.out.println("������ �߸��Ǿ����ϴ�");
			md.show("������ �߸��Ǿ����ϴ�");
			return;
		}
		if(c==null){
			md.show("ã�� �� �����ϴ�");
			return;
		}
		phonetf.setText(c.getPhone());
		nametf.setText(c.getName());
		hotKeytf.setText(c.getHotKey()+"");//String<=int
	}
/** ArrayList�� �ִ� ����Ÿ�� List �� ǥ���Ѵ�.*/
	public void showList(){
		ArrayList<Customer> v=cl.allCust();
		li.removeAll();
		for(Customer cv:v){
			li.add(cv.toString());
		}
	}
/** List�� �׸��� ����(Ŭ��)�Ǿ� ItemEvent�� �߻� ���� �� ����ȴ�. 
ItemListener �� itemStateChanged method Overrinding */
	public void itemStateChanged(ItemEvent e){
			String str=li.getSelectedItem();
			String[] ss=str.split("   ");
			nametf.setText(ss[0]);
			phonetf.setText(ss[1]);
			hotKeytf.setText(ss[2]);

	}
	public static void main(String[] args){
		new ContactMain();
	}
}
