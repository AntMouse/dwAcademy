package chap9_inheritance_polymorphism;

class Vehicle3 {
	double cost = 100.0;
	static int age = 1;
	public void move() {
		System.out.println("Vehicle3::move()");
	}
	public static void sm() {
		System.out.println("Vehicle3::sm()");
	}
}
class Car3 extends Vehicle3 {
	double cost = 20_000.0;
	static int age = 2;
	@Override
	public void move() {
		System.out.println("Car3::move()");
	}
	public static void sm() {
		System.out.println("Car3::sm()");
	}
}

public class C3TestVehicle3 {
	public static void main(String[] args) {
		Vehicle3 v = new Car3();
		System.out.println(v.cost);
		System.out.println(v.age);
		v.sm();
		v.move();
	}

}
