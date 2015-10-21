package edu.jaen.java.io;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int su1 = s.nextInt(8);
		int su2 = s.nextInt(8);
		System.out.println(su1 + ":" + su2);
		int sum = su1 + su2;
		System.out.println(Integer.toString(sum, 8));
	}

}
