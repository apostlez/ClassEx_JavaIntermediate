package com.lge.adjava;

import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {
		int[] su = {34, 21, 56, 65, 675, 1, 4};
		Arrays.sort(su);
		
		for(int item : su) {
			System.out.print(item + " ");
		}
		System.out.println("\n============");
		System.out.println(Arrays.toString(su));
		Arrays.fill(su, 0);
		System.out.println("============\n" + Arrays.toString(su));
	}

}
