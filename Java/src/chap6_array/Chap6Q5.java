package chap6_array;

public class Chap6Q5 {

	public static void main(String[] args) {
		String[] dinoTypeList = {
		"T-Rex", "Brachiodaurus", "Stegosaurus", "Ankylosaurus", "Giganotosaurus"
		};
		int[][] dinoAgeList = {
				{7,8,13,19,20,14,3},
				{2,4,10,5,20,15,3},
				{1,8,15,10,14,14,21},
				{5,1,11,17,8,14,15},
				{7,16,13,19,6,9,2},
		};
		int[] dinoTypeSum = new int[dinoAgeList.length];
		int dinoTotal = 0;
		int dinoTotalAverage = 0;
		
		for (int i = 0; i < dinoAgeList.length; i++) {
			for (int j = 0; j < dinoAgeList[i].length; j++) {
				dinoTypeSum[i] += dinoAgeList[i][j];
				dinoTotal += dinoAgeList[i][j];
				dinoTotalAverage++;
			}
			System.out.println(dinoTypeList[i] + "의 평균 나이는 " +
			((double)dinoTypeSum[i] / dinoAgeList[i].length) + "입니다.");
		}
		System.out.println("공원의 모든 공룡의 나이 평균은 " +
		((double)dinoTotal / dinoTotalAverage) + "입니다.");

	}

}
