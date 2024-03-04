package chap3_order_of_precedence;

public class Chap3Q4 {

	public static void main(String[] args) {
		int maxCapacity = 1000;
		int currentPeople = 788;
		boolean maxCapacityCheck =  (currentPeople > maxCapacity) ? true : false;
		
		System.out.println("Max capacity reached : " + maxCapacityCheck);
	}

}
