package chap11_interfance;

interface I1 {
	public abstract void m1();
	void m2();
	private void m3() {};
}

interface I2 {
	public static final int VALUE1 = 1;
	int VALUE2 = 2;
}

interface Moveable {
	String HOW = "walk";
	void move();
}

public class C1Dog implements Moveable {
	// 1
	// void move() {}
	@Override
	// 2
	public void move() {
		System.out.println("Dog::move()");
	}

	public static void main(String[] args) {
		// 3
		// HOW = "walk";
		// 4
		System.out.println(Moveable.HOW);
		// 5 
		System.out.println(HOW);
		// 6
		// move();
		// 7
		new C1Dog().move();
	}

}
