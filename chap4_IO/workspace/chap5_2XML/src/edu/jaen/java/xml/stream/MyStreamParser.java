package edu.jaen.java.xml.stream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class MyStreamParser {

	public ArrayList<Check> getList(String url){
		ArrayList<Check> list=new ArrayList<Check>();
		BufferedReader br=null;
		Check ch=null;
		String msg=null;
		String[] items=null;
		try{	
			br=new BufferedReader(
					new InputStreamReader(
					new URL(url).openConnection().getInputStream()));
			while((msg=br.readLine()) != null){
				items=msg.split(",");
				ch=new Check();
				ch.setCode(items[0]);
				ch.setDate(items[1]);
				ch.setClean(items[2]);
				ch.setReady(items[3]);
				ch.setResponse(items[4]);
				ch.setRequest(items[5]);
				list.add(ch);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
