package edu.jaen.java;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// AWT 를 이용하여 주소록에 등록/수정/삭제 기능을 구현해 본다.

public class ContactMain implements ActionListener,ItemListener{
	Frame f=new Frame("Phone Book");
	Label custl=new Label("Phone Book",Label.CENTER);
	Label namel=new Label("이  름",Label.CENTER);
	Label phonel=new Label("핸드폰",Label.CENTER);
	Label hotKeyl=new Label("단축키",Label.CENTER);
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
		md=new MessageDialog(f,"경  고");//Dialog 생성(owner,title)
		createGUI();
		addEvent();
		showList();
	}
/** GUI 를 생성한다.*/
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
/** 이벤트를 등록 또는 처리한다. */
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
/** 버튼이 눌리거나 클릭되어 ActionEvent가 발생했을 때 실행된다. 
ActionListener 의 actionPerformed method Overrinding*/
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
/** TextField의 내용을 지운다. */
	public void clearText(){
			phonetf.setText("");
			nametf.setText("");
			hotKeytf.setText("");
	}
/** Insert Button이 눌렸을 때 ActionPerformed Method에 의해 호출된다.*/
	public void insertRecord(){
		//int<==String   
		//trim()...문자열의 공백 제거
		String phone=phonetf.getText().trim();
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(phone.equals("")||name.equals("")||hotKey.equals("")){
			md.show("비어있는 항목이 있습니다");
			return;
		}
		if(cl.search(Integer.parseInt(hotKey))!=null){
			md.show("이미 있는 단축키입니다.");
			return;
		}
		cl.addCust(name,phone,Integer.parseInt(hotKey));
		showList();
		clearText();
	}
/** Delete Button이 눌렸을 때 ActionPerformed Method에 의해 호출된다.*/
	public void deleteRecord(){		
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(name.equals("")||hotKey.equals("")){
			md.show("선택이 잘못 되었습니다");
			return;
		}
		if(!name.equals("")){
			if(cl.search(name)==null){
				md.show("존재하지 않는 이름입니다.");
				return;
			}else{
				cl.delete(name);
			}
		}else{
			if(cl.search(Integer.parseInt(hotKey))==null){
				md.show("존재 하지 않는 단축키입니다.");
				return;
			}else{
				cl.delete(hotKey);
			}
		}
		showList();
		clearText();
	}
/** Update Button이 눌렸을 때 ActionPerformed Method에 의해 호출된다.*/
	public void updateRecord(){
		String phone=phonetf.getText().trim();
		String name=nametf.getText().trim();
		String hotKey=hotKeytf.getText().trim();
		if(phone.equals("")||name.equals("")||hotKey.equals("")){
			md.show("비어있는 항목이 있습니다");
			return;
		}
		if(cl.search(name)== null){
			md.show("존재하는 이름이 없습니다.");
			return;
		}
		cl.updateCust (name, phone,Integer.parseInt( hotKey));
		showList();
		clearText();
	}
/** Search Button이 눌렸을 때 ActionPerformed Method에 의해 호출된다.*/
	public void searchRecord(){
		Customer c=null;
		String hotKey=hotKeytf.getText().trim();
		String name=nametf.getText().trim();
		if(!(name.equals(""))){
			c=cl.search(name);
		}else if(!(hotKey.equals(""))){
			c=cl.search(Integer.parseInt(hotKey));
		}else {
			//System.out.println("선택이 잘못되었습니다");
			md.show("선택이 잘못되었습니다");
			return;
		}
		if(c==null){
			md.show("찾을 수 없습니다");
			return;
		}
		phonetf.setText(c.getPhone());
		nametf.setText(c.getName());
		hotKeytf.setText(c.getHotKey()+"");//String<=int
	}
/** ArrayList에 있는 데이타를 List 에 표시한다.*/
	public void showList(){
		ArrayList<Customer> v=cl.allCust();
		li.removeAll();
		for(Customer cv:v){
			li.add(cv.toString());
		}
	}
/** List의 항목이 선택(클릭)되어 ItemEvent가 발생 했을 때 실행된다. 
ItemListener 의 itemStateChanged method Overrinding */
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
