package chap7_method;

public class C1Method4 {

	public static void main(String[] args) {
		int sum = add(3, 4);
		System.out.println(sum);
		double addition = add(3.0, 4.0);
		System.out.println(addition);
	}
	
	public static int add(int x, int y) {
		System.out.println("add(int, int)");
		return x + y;
	}
	
	public static double add(double x, double y) {
		System.out.println("add(double, double)");
		return x + y;
	}

}
