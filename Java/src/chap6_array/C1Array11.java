package chap6_array;

import java.util.Arrays;

public class C1Array11 {

	public static void main(String[] args) {
		int[] originalArray = {10,20,30,40,50};
		int newLength = 7;
		int[] resizedArray = Arrays.copyOf(originalArray, newLength);
		System.out.println("Original array : " + Arrays.toString(originalArray));
		System.out.println("Resized array : " + Arrays.toString(resizedArray));
		

	}

}
