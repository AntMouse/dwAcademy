package chap4_scope;

public class C2If3 {

	public static void main(String[] args) {
		int x = 4, y = 4;
		if (x > y) {
			System.out.println(x + " > " + y);
		} else if (x < y) {
			System.out.println(x + " < " + y);
		} else {
			System.out.println(x + " == " + y);
		} 
		System.out.println("Here");
	}

}
