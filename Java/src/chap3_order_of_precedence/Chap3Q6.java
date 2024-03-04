package chap3_order_of_precedence;

public class Chap3Q6 {

	public static void main(String[] args) {
		int safetyRating = 2;
		int safetyThreshold = 4;
		String safetyCheck = (safetyRating > safetyThreshold) ? "안전 등급이 임계값을 넘었습니다." :
			"안전 등급이 임계값을 넘지 않았습니다.";
		
		System.out.println(safetyCheck);
	}

}
