package edu.jaen.java.xml.basic;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
//import org.apache.crimson.tree.XmlDocument;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class DOMTest09_DataToXml{
		File outputXml= new File("noticeList.xml");
		Document doc;
		Element root;
		DocumentBuilder parser;
public DOMTest09_DataToXml(){
	try{
		DocumentBuilderFactory factory=
				DocumentBuilderFactory.newInstance();
		parser=factory.newDocumentBuilder();
		if(outputXml.exists()){
			doc=parser.parse(outputXml);
			root=doc.getDocumentElement();
		}else{
			doc=parser.newDocument();
			root=doc.createElement("공지목록");
			doc.appendChild(root);
		}
	//	doc.normalize();
	}catch(Exception e){e.printStackTrace();}
}
public Document listXml(){
		return doc;
}
public void insert(String name, String date, String title,String content){
	int num=getMaxNum()+1;
	Element e=doc.createElement("공지");
	//root.appendChild(e);
	root.insertBefore(e,root.getFirstChild());
	e.setAttribute("번호",num+"");

	Element e1=doc.createElement("이름");
	Text t1=doc.createTextNode(name);
	e1.appendChild(t1);
	e.appendChild(e1);

	Element e2=doc.createElement("날짜");
	Text t2=doc.createTextNode(date);
	e2.appendChild(t2);
	e.appendChild(e2);

	Element e3=doc.createElement("제목");
	Text t3=doc.createTextNode(title);
	e3.appendChild(t3);
	e.appendChild(e3);

	Element e4=doc.createElement("내용");
	Text t4=doc.createTextNode(content);
	e4.appendChild(t4);
	e.appendChild(e4);
	saveXml();
}
synchronized public void saveXml(){
	try{
//doc.normalize();
/*
		XmlDocument xmlDoc=(XmlDocument)doc;
		xmlDoc.normalize() ;
		BufferedWriter bw=new BufferedWriter(
					new FileWriter(outputXml));
		xmlDoc.write(bw,"euc-kr");
		bw.close();
	*/
			TransformerFactory tfactory=TransformerFactory.newInstance();
			Transformer xslProcessor=tfactory.newTransformer();
			xslProcessor.setOutputProperty("indent","yes");
			xslProcessor.setOutputProperty("encoding","euc-kr");
			xslProcessor.transform(new DOMSource(doc),new StreamResult(outputXml));
	}catch(Exception e){e.printStackTrace();}
}
public Document viewXml(String num){
	Document newDoc=parser.newDocument();
	Node newRoot=newDoc.createElement("공지목록");
	newDoc.appendChild(newRoot);

	NodeList list=doc.getElementsByTagName("공지");
	for(int i=0;i<list.getLength();i++){
		Node n=list.item(i);
		String value=n.getAttributes().getNamedItem("번호")
								.getNodeValue();
		if(value.equals(num)){
		  newRoot.appendChild((Element)(newDoc.importNode(n,true)));
		  break;
		}
	}
	return newDoc;
}
public void deleteXml(String num){
  NodeList list=doc.getElementsByTagName("공지");
	for(int i=0;i<list.getLength();i++){
		Node n=list.item(i);
		String value=n.getAttributes().getNamedItem("번호").getNodeValue();
		if(value.equals(num)){
			Node parent=n.getParentNode();
			parent.removeChild(n);
			break;
		}
	}
	saveXml();
}
public void updateXml(String num,String name,String date,String title,String content)
	{		
		NodeList list = doc.getElementsByTagName("공지");
		for( int i = 0; i < list.getLength(); i++ ){
			Node n=list.item(i);
			String str = n.getAttributes().getNamedItem("번호").getNodeValue();
			System.out.println("viewXml()............:"+str);
			if( str.equals(num) ){
					for(Node ch = n.getFirstChild();ch != null;ch = ch.getNextSibling()){
						if( ch.getNodeName().trim().equals("이름")){
								ch.getFirstChild().setNodeValue( name );
						}else if(ch.getNodeName().trim().equals("날짜")){
								ch.getFirstChild().setNodeValue( date );
						}else if(ch.getNodeName().trim().equals("제목")){
								ch.getFirstChild().setNodeValue( title );
						}else if(ch.getNodeName().trim().equals("내용")){
								ch.getFirstChild().setNodeValue( content );
						}//end else if
					}
					break;
			}//end if
	  	}//end for
		saveXml();
	}

public int getMaxNum(){
	NodeList list=doc.getElementsByTagName("공지");
	int max=list.getLength();
	for(int i=0;i<list.getLength();i++){
		String value=list.item(i).getAttributes().
								getNamedItem("번호").getNodeValue();
		/*
			Node n=list.item(i);
		  NamedNodeMap map=n.getAttributes();
			Node attn=map.getNamedItem("번호");
			String value=attn.getNodeValue();
		*/
		int number=Integer.parseInt(value);
		if(number>max) max=number;
	}
	return max;
}
public static void main(String[] a){
	DOMTest09_DataToXml xml=new DOMTest09_DataToXml();
	//xml.insert("홍길동","2004.12.22","연습중","잘 들어갈까나??");
	//	xml.deleteXml("3");
	//System.out.println(xml.viewXml("3"));
	xml.updateXml("1","홍길동","2004.12.22","수정중","잘 들어갈까나??");
}
}
