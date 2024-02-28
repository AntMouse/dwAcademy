package chap11_interfance;

// 1
sealed interface Driveable permits Vehicle {
	void drive();
}
// 2
sealed class Vehicle implements Driveable permits Car {
	private int wheelMode;
	Vehicle(int wheelMode) {
		this.wheelMode = wheelMode;
	}
	public int getWheelMode() {
		return this.wheelMode;
	}
	public void setWheelMode(int wheelMode) {
		this.wheelMode = wheelMode;
	}
	
	@Override
	public void drive() {
		System.out.println(this.getClass().getSimpleName() + "::drive()");
		System.out.println(this.getClass().getSimpleName() + "wheelMode : " + this.wheelMode);
		System.out.println();
	}
}

sealed class Car extends Vehicle permits Saloon {
	Car(int wheelMode) {
		super(wheelMode);
	}
    @Override
    public void drive() {
        super.drive();
    }
}
non-sealed class Saloon extends Car {
    Saloon(int wheelMode) {
        super(wheelMode); // Car 클래스의 생성자 호출
    }
    @Override
    public void drive() {
        super.drive();
    }
} 
class Volvo extends Saloon{
    Volvo(int wheelMode) {
        super(wheelMode); // Saloon 클래스의 생성자 호출
    }
    @Override
    public void drive() {
        super.drive();
    }
}
class Ford extends Saloon{
    Ford(int wheelMode) {
        super(wheelMode); // Saloon 클래스의 생성자 호출
    }
    @Override
    public void drive() {
        super.drive();
    }
}
// 3
// class Window extends Vehicle {} 
// 4
// class Chair extends Car{}   
// 5
// class Table implements Driveable{} 

public class C7SealedTest {
    Vehicle[] vehicles = new Vehicle[10];

    public C7SealedTest() {
        // 배열의 각 요소를 다양한 클래스의 객체로 초기화
        for (int i = 0; i < vehicles.length; i++) {
            if (i % 4 == 0) {
                vehicles[i] = new Vehicle(4);
            } else if (i % 4 == 1) {
                vehicles[i] = new Car(4);
            } else if (i % 4 == 2) {
                vehicles[i] = new Volvo(8);
            } else {
                vehicles[i] = new Ford(10);
            }
        }
    }

    public static void main(String[] args) {
        C7SealedTest test = new C7SealedTest();

        for (Vehicle vehicle : test.vehicles) {
            vehicle.drive();
        }
    }
}