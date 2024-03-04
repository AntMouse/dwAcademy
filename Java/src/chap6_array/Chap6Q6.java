package chap6_array;

public class Chap6Q6 {

	public static void main(String[] args) {
		String[][] parkMap = {
				{"A 파크", "티라노 둥지", "익룡 둥지", "관람차", "안내"},
				{"B 파크", "음식점", "기념품 가게", "화장실", "공룡 박물관"},
				{"C 파크", "광장", "새끼 공룡 둥지", "행사장", "아동 보호소"},
				{"D 파크", "화장실", "초식 공룡 둥지", "공룡알 전시장", "암모나이트 전시장"},
				{"E 파크", "육식 공룡 둥지", "화장실", "무거운 공룡 전시장", "공룡 설명회"}
	        };

	        System.out.println("공원 지도를 표시합니다.");
	        for (int i = 0; i < parkMap.length; i++) {
	        	System.out.print(parkMap[i][0] + " : ");
	            for (int j = 1; j < parkMap[i].length; j++) {
	            	if (j != (parkMap[i].length - 1)) {
	            		System.out.print(parkMap[i][j] + ", ");
					} else {
						System.out.print(parkMap[i][j]);
					}
	            }
	            System.out.println();
	        }
		

	}

}
