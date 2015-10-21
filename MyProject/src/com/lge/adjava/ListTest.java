package com.lge.adjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListTest {

	public static void main(String[] args) {
		//Set<Friend> s = new HashSet<Friend>();
		List<Friend> s = new ArrayList<Friend>();
		s.add(new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		s.add(new Friend("Hong Gildong", "010-1234-5678", "gildong.hong@hwoalbindang.org"));
		s.add(new Friend("Go Gildong", "010-1234-5678", "gildong.go@hwoalbindang.org"));
		s.add(new Friend("Dulli", "010-1234-5678", "Dulli@hwoalbindang.org"));
		s.add(new Friend("Doughnur", "010-1234-5678", "Doughnur@hwoalbindang.org"));
		s.add(new Friend("Ddochi", "010-1234-5678", "Ddochi@hwoalbindang.org"));

		s.forEach((item) -> {System.out.println(item.toString());});
		System.out.println("======== sorted =========");
		Collections.sort(s);
		//s.sort((src, tgt) -> {return src.getName().compareTo(tgt.getName());});
		s.forEach((item) -> {System.out.println(item.toString());}); 
	}

}
