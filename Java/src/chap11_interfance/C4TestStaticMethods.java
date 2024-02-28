package chap11_interfance;

interface I {
	// static void m0();	
	static int m1() {
		return 3;
	}
		
}

public class C4TestStaticMethods {

	public static void main(String[] args) {
		// System.out.println(m1());
		System.out.println(I.m1());
	}

}
