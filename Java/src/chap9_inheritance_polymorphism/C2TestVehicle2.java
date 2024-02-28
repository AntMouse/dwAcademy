package chap9_inheritance_polymorphism;

class Vehicle2 {
	public void move() {
		System.out.println("Vehicle::move");
	}
}
class Car2 extends Vehicle2 {
	@Override 
	public void move() {
		System.out.println("Car::move()");
	}
	public void wheels() {
		System.out.println("Car::wheels()");
	}
}
class Boat2 extends Vehicle2 {
	@Override 
	public void move() {
		System.out.println("Boat::move()");
	}
	public void floats() {
		System.out.println("Boat::floats()");
	}
}
class Saloon2 extends Car2 {
	@Override 
	public void move() {
		System.out.println("Saloon::move()");
	}
}
class Convertible2 extends Car2 {
	
}

public class C2TestVehicle2 {
	
	public static void doAction(Vehicle2 v) {
		v.move();
	}
	
	public static void main(String[] args) {
		Vehicle2 v = new Car2();
		
		doAction(v);
		doAction(new Boat2());
		doAction(new Saloon2());
		doAction(new Convertible2());
		
		System.out.println();
		
		v.move();
		// v.wheels();
		Car2 car = (Car2) v;
		car.wheels();
		v = new Boat2();		
		v.move();
		// v.floats();
		v = new Saloon2();
		v.move();
		v = new Convertible2();
		v.move();
		// Saloon2 s = (Saloon2) new Vehicle2();
		// s.move();
		
	}
	
}
