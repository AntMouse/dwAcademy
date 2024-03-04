package chap3_order_of_precedence;

public class Chap3Q5 {

	public static void main(String[] args) {
		int dino1Age = 7, dino2Age = 12;
		String ageDifference = (dino1Age > dino2Age) ? 
		"dino1 공룡이 dino2 공룡보다 " + (dino1Age - dino2Age) + "살 만큼 나이가 많습니다." :
		"dion2 공룡이 dino1 공룡보다 " + (dino2Age - dino1Age) + "살 만큼 나이가 많습니다.";
		System.out.println(ageDifference);
	}

}
