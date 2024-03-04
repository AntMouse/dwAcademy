package chap3_order_of_precedence;

public class Chap3Q2 {

	public static void main(String[] args) {
		int dinoWeight = 5500; // 공룡 체중
		double foodPerWeightUnit = 0.05; // 체중당 필요한 먹이 양
		
		System.out.println("공룡 체중 1kg당 " + foodPerWeightUnit + "kg 만큼"
		+ "먹이가 필요하다. 체중이 " + dinoWeight + "kg인 공룡은 총 " + 
		dinoWeight * foodPerWeightUnit + "kg 만큼 먹어야 한다.");
			
		
	}

}
