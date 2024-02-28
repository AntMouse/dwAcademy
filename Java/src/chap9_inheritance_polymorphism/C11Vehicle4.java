package chap9_inheritance_polymorphism;
// 1
public sealed class C11Vehicle4 permits Car4 {
	@Override
    public String toString() {
		return "Vehicle4::toString()";
    }

	public static void main(String[] args) {
		C11Vehicle4 vehicle4 = new C11Vehicle4();
		Car4 car4 = new Car4();
		Saloon4 saloon4 = new Saloon4();
		Volvo4 volvo4 = new Volvo4();
		Ford4 ford4 = new Ford4();
		
		System.out.println(vehicle4);
		System.out.println(car4);
		System.out.println(saloon4);
		System.out.println(volvo4);
		System.out.println(ford4);
		
		System.out.println();
		
		C11Vehicle4[] vehicles4 = new C11Vehicle4[] 
		{new C11Vehicle4(), new Car4(), new Saloon4(), new Volvo4(), new Ford4()};
		for (C11Vehicle4 vehicle : vehicles4) {
		    System.out.println(vehicle);
		}
	}

}
//2
sealed class Car4 extends C11Vehicle4 permits Saloon4 {
	@Override
    public String toString() {
		return "Car2::toString()";
    }
}
//3 sealed class Truck extends Test9_11 {}
//4
non-sealed class Saloon4 extends Car4 {
	@Override
    public String toString() {
        return "Saloon2::toString()";
    }
}
class Volvo4 extends Saloon4 {
	@Override
    public String toString() {
        return "Volvo::toString()";
    }
}
class Ford4 extends Saloon4 {
	@Override
    public String toString() {
        return "Ford::toString()";
    }
}
//5 class Convertible extends Car{}
