package chap6_array;

import java.util.Iterator;

public class C1Array1 {

	public static void main(String[] args) {
		String[] names = {"Maria", "Fatiha", "Pradeepa", "Sarah"}; 
		int index = 5; 
		
		if (index >= 0 && index < names.length) {
			System.out.println("Element at index " + index + "." + names[index]);
		} else {
			System.out.println("Invalid index: " + index);
		}
		
		int[] results = {10, 20, 30, 40, 50};
		for (int i = 0; i < results.length; i++) {
			System.out.println("Element at " + i + ": " + results[i]);
		}
	}

}
