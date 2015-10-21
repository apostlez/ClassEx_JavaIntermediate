package com.lge.adjava;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest2 {

	public static void main(String[] args) {
		//Set<Friend> s = new HashSet<Friend>();
		Set<Friend> s = new TreeSet<Friend>();
		s.add(new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		s.add(new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		s.add(new Friend("Go Gildong", "010-1234-5678", "gildong.go@hwoalbindang.org"));
		s.add(new Friend("Dulli", "010-1234-5678", "Dulli@hwoalbindang.org"));
		s.add(new Friend("Doughnur", "010-1234-5678", "Doughnur@hwoalbindang.org"));
		s.add(new Friend("Ddochi", "010-1234-5678", "Ddochi@hwoalbindang.org"));

		s.forEach((item) -> {System.out.println(item.toString());}); 
	}

}
