package chap3_order_of_precedence;

public class Chap3QProject1 {

	public static void main(String[] args) {
		int currentDino = 5500;
		double currentDinoFood = 0.05; // 체중당 필요한 먹이 양
		int currentDinoTodayFoodNumber = 3; // 하루에 3번 먹는다
		
		System.out.println("체중이 " + currentDino + "kg인 공룡은 하루에 " + 
		currentDinoTodayFoodNumber + "끼니를 먹습니다.");
        System.out.println("하루에 총 " + currentDino * currentDinoFood + "kg의 음식이 필요합니다.");
        System.out.println("따라서 각 끼니마다 " + 
        (currentDino * currentDinoFood) / currentDinoTodayFoodNumber + "kg의 음식이 필요합니다.");
	}

}
