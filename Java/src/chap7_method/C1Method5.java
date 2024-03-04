package chap7_method;

public class C1Method5 {

	public static void main(String[] args) {
		m1();
		m1(1);
		m1(1,2);
		m1(1,2,3);
		
	}
	
	public static void m1(int...args) {
		int sum = 0;
		for (int i : args) {
			sum += i;
		}
		System.out.println(sum);
	}

}
