package com.lge.adjava;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	public static void main(String[] args) {
		//Set<String> s = new HashSet<String>(); // 5.0
		//Set<String> s = new HashSet<>(); // 7.0
		//Set s = new HashSet();
		Set<String> s = new TreeSet<String>();
		s.add("seoul");
		s.add("buchon");
		s.add("inchon");
		s.add("kwangju");
		s.add("seoul");

		s.parallelStream().forEach(a -> {System.out.println(a);});
		for(Object str : s) {
			System.out.println(str);
		}
	}

}
