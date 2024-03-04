package chap6_array;

import java.util.Arrays;

public class C1Array8 {

	public static void main(String[] args) {
		int[] results = {10,20,30,40,50};
		int target = 30;
		int index = Arrays.binarySearch(results, target);
		System.out.println("Index of " + target + " : " + index);

	}

}
