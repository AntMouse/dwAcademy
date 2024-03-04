package chap3_order_of_precedence;

public class Chap3QProject4 {

	public static void main(String[] args) {
		int foodNumber = 2;
		int dinoWeight = 4000;
		double dinoFood = dinoWeight * 0.05;
		
		System.out.println("우리의 " + dinoWeight + "kg 공룡은 매일 " + dinoFood +
		"kg의 음식이 필요하고, 그것은 매 끼 " + dinoFood / foodNumber + "kg만큼 주어야 한다.");
	}

}
