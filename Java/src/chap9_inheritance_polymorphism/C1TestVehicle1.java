package chap9_inheritance_polymorphism;

class Vehicle1 {
	public String toString() {
		return "Vehicle::toString()";
	}
}

class Car1 extends Vehicle1 {}
class Boat1 extends Vehicle1 {}
class Saloon1 extends Car1 {}
class Convertible1 extends Car1 {}

public class C1TestVehicle1 {

	public static void main(String[] args) {
		Vehicle1 vehicle1 = new Vehicle1();
		System.out.println(vehicle1.toString());
		Car1 car = new Car1();
		System.out.println(car);
		Saloon1 saloon = new Saloon1();
		System.out.println(saloon);
		System.out.println(new C1TestVehicle1().toString());

	}

}
