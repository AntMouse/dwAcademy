package chap11_interfance;

interface InefficienTennis {
	static void forehand() {
		// 1
		System.out.println("Move into position");
		// 2
		System.out.println("Hitting a forehand");		
		// 3
		System.out.println("Move back into ready position");
	}
	default void backhand() {
		// 4
		System.out.println("Move into position");
		// 5
		System.out.println("Hitting a backhand");
		// 6
		System.out.println("Move back into ready position");
	}
	default void smash() {
		// 7
		System.out.println("Move into position");
		// 8
		System.out.println("Hitting a smash");
		// 9
		System.out.println("Move back into ready position");
	}
}

interface EfficientTennis {
	private static void hit(String stroke) {
		System.out.println("Move into position");
		System.out.println("Hitting a " + stroke);	
		System.out.println("Move back into ready position");
	}
	default void backhand() {
		hit("backhand");
	}
	static void forehand() {
		// smash();
		hit("forehand");
	}
	private void smash() {
		hit("smash");
	}
	// void volley() { hit("volley"); }
}

public class C6SportTest implements EfficientTennis {

	public static void main(String[] args) {
		new C6SportTest().backhand();
		EfficientTennis.forehand();
		// 1 new SportTest().hit("Serve");

	}

}
