package chap6_array;

import java.util.Arrays;

public class Chap6Q2 {

	public static void main(String[] args) {
		String[] dinoTypeList = {
		"T-Rex", "Brachiodaurus", "Stegosaurus", "Ankylosaurus", "Giganotosaurus"
		};		
		int[][] dinWeightList = {
				{4000, 2750, 5000, 4923, 3700, 4300, 4974},
				{7420, 7947, 6954, 6174, 7157, 7050, 7947},
				{1500, 1974, 1050, 1347, 1417, 1627, 1344},
				{6000, 5423, 5760, 6784, 6010, 6864, 5974},
				{9000, 9433, 9125, 9274, 9913, 9007, 9435}
		};	
		int maxWeight = 0;
		String outputDinoType = null;
		
		for (int i = 0; i < dinWeightList.length; i++) {
			for (int j = 0; j < dinWeightList[i].length; j++) {
				if (dinWeightList[i][j] > maxWeight) {
					maxWeight = dinWeightList[i][j];
					outputDinoType = dinoTypeList[i];
				}
			}
		}
		
		if (maxWeight != 0) {
			System.out.println("공원에서 가장 무거운 공룡의 무게는 : " + maxWeight + "이다.");
			System.out.println("해당 공룡은 " + outputDinoType + "입니다.");
		}

	}
}
