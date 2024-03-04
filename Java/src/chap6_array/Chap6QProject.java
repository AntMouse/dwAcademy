package chap6_array;

public class Chap6QProject {

	public static void main(String[] args) {
		String[][] dinoRecordList = {
				
		{"공룡1", "공룡2", "공룡3", "공룡4", "공룡5",
		"공룡6", "공룡7", "공룡8", "공룡9", "공룡10"}, // 1. 이름
		{"4", "3", "7", "20", "17", "12", "11", "2", "16", "19"}, // 2. 나이
		{"T-Rex", "Brachiodaurus", "Stegosaurus", "Ankylosaurus", "Giganotosaurus",
		"Mosasaurus", "Elasmosaurus", "Elasmosaurus", "Triceratops", "Pteranodon"
		}, // 3. 종
		{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, // 4. 우리 번호
		
		};
		int dinoTotalAge = 0;
		int dinoTotalWeight = 0;
		
		int[] dinWeightList = {
		4500,3700,5400,6243,7489,3145,3244,9978,3214,4678
		};
		
		for (int i : dinWeightList) {
			dinoTotalWeight += i;
		}

		for (int i = 0; i < dinoRecordList[0].length; i++) {
			System.out.println(dinoRecordList[0][i] + "의 정보입니다.");
			System.out.println("이름 : " + dinoRecordList[0][i]);
			System.out.println("나이 : " + dinoRecordList[1][i]);
			System.out.println("종 : " + dinoRecordList[2][i]);
			System.out.println("우리 번호 : " + dinoRecordList[3][i]);
			System.out.println();
			
			dinoTotalAge += Integer.parseInt(dinoRecordList[1][i]);
		}
		System.out.println("공룡의 평균 나이 : " + ((double)dinoTotalAge / dinoRecordList[0].length));
		System.out.println("공룡의 평균 무게 : " +  ((double)dinoTotalWeight / dinoRecordList[0].length));
		

	}

}
