package chap9_inheritance_polymorphism;

class Vehicle {
	
}
class Car extends Vehicle {
	private int numDoors;
	Car (int numDoors) {
		this.numDoors = numDoors;
	}
	public int getNumDoors() {
		return numDoors;
	}
	public String onRoad() {
		return "I can move ont hte road";
	}
}
class Boat extends Vehicle {
	
}
class Train extends Vehicle {
	
}
class Input extends Vehicle {
	
}

public class C8Protected {
	public static void patternMatchingSwitch(Vehicle v) {	
		if (v instanceof Boat) {
            System.out.println("It's a Boat");
        } else if (v instanceof Train) {
            System.out.println("It's a Train");
        } else if (v instanceof Car) {
            Car c = (Car) v;
            if (c.getNumDoors() == 4) {
                System.out.println("Saloon " + c.onRoad());
            } else if (c.getNumDoors() == 2) {
                System.out.println("Convertible: " + c.onRoad());
            } else {
                System.out.println("Invalid type");
            }
        } else {
            System.out.println("Invalid type");
        }
	}
	
	/*
	public static void patternMatchingSwitch2(Vehicle v) {
		System.out.println(
		switch (v) {
		case Boat b -> "It's a Boat";
		case Train t -> "It's a Train";
		case Car c when c.getNumDoors() == 4 -> "Saloon " + c.onRoad();
		case Car c when c.getNumDoors() == 2 -> "Convertible : " + c.onRoad();
		case null, deafult -> "Invalid type";
		}		
		);
	}
	*/

	/*
	public static void patternMatchingSwitch3(Vehicle v) {
        System.out.println(
        switch(v) {
        	case Boat b -> "It's a Boat";
        	case Train t -> "It's a Train";
        	case Car c when c.getNumDoors() == 4 -> "Saloon " + c.onRoad();
        	case Car c when c.getNumDoors() == 2 -> "Convertible : " + c.onRoad();
        	case null, default -> "Invalid type";
            }
        );
    }
	*/
    
	public static void main(String[] args) {
		Car car = new Car(2);
        Boat boat = new Boat();
        Train train = new Train();
        Input input = new Input();
        patternMatchingSwitch(car);
        patternMatchingSwitch(boat);
        patternMatchingSwitch(train);
        patternMatchingSwitch(input);
	}

}
