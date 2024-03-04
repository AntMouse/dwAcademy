package chap3_order_of_precedence;

public class Chap3QProject3 {

	public static void main(String[] args) {
		int foodNumber = 2;
		int dinoWeight = 4000;
		double dinoFood = dinoWeight * 0.05;
		
		System.out.println("공룡 체중이 " + dinoWeight + "kg 이고, 하루에 " + foodNumber 
		+ "끼를 먹는다면 매 끼니마다 " + dinoFood / foodNumber + "kg만큼 먹어야한다.");
	}

}
