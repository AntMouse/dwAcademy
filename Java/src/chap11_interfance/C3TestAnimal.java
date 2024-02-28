package chap11_interfance;

interface Moveable2 {
	default void move() {
		System.out.println("Moving");
	}
}
class Cheetah implements Moveable2 {
	@Override
	public void move() {
		System.out.println("Moving very fast!");
	}
}
class Elephant implements Moveable2 {}

public class C3TestAnimal {

	public static void main(String[] args) {
		// Moveable2 m1 = new Moveable2();
		// 1
		Moveable2 cheetah = new Cheetah();
		cheetah.move();
		Moveable2 elephant = new Elephant();
		elephant.move();

	}

}
