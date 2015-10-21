package com.lge.adjava;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Friend> m = new HashMap<>();
		
		m.put("Hong Gildong", new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		m.put("Go Gildong", new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		m.put("Go Gildong", new Friend("Go Gildong", "010-1234-5678", "gildong.go@hwoalbindang.org"));
		m.put("Dulli", new Friend("Dulli", "010-1234-5678", "Dulli@hwoalbindang.org"));
		m.put("Doughnur", new Friend("Doughnur", "010-1234-5678", "Doughnur@hwoalbindang.org"));
		m.put("Ddochi", new Friend("Ddochi", "010-1234-5678", "Ddochi@hwoalbindang.org"));

		for(String s : m.keySet()) {
			System.out.println(m.get(s));
		}
		

	}

}
