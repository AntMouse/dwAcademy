package chap7_method;

import java.util.Scanner;

public class Chap7Q3 {

	public static void main(String[] args) {
		int[] dinoAgeList = {4, 2, 14, 7, 9, 3, 8, 5, 6, 7};
		
		System.out.println("공룡들의 평균 나이는 " + dinoAgeAverage(dinoAgeList) + 
		"입니다.");
	}
	
	public static double dinoAgeAverage(int[] dinoAgeArrange) {
		int dinoAgeSum = 0;
		for (int i = 0; i < dinoAgeArrange.length; i++) {
			dinoAgeSum += dinoAgeArrange[i];
		}
		
		double dinoAgeAverageValue = ((double)dinoAgeSum / dinoAgeArrange.length);
		return dinoAgeAverageValue;
	}

}
