package chap11_interfance;

//추상 클래스 정의
abstract class Vehicle2 {
	// 추상 메서드 - 모든 차량은 이동할 수 있어야 함
	abstract void move();
	abstract void travel();
		
	private String type;
	protected String model;

	// 생성자
	public Vehicle2(String type, String model) {
		this.type = type;
		this.model = model;
	}

	// 일반 메서드 - 차량 정보 출력
	public void displayInfo() {
	System.out.println("Type: " + type);
	System.out.println("Model: " + model);
	}
}

//구현 클래스 - Jeep
class Jeep extends Vehicle2 {
	// 생성자
	public Jeep(String model) {
		super("Jeep", model);
	}

	// 이동 메서드 구현
	@Override
	void move() {
		System.out.println("Jeep(" + super.model + ")가 이동합니다.");
	}
	// 이동 메서드 구현 2
	@Override
	void travel() {
		System.out.println("Jeep(" + super.model + ")가 도로에서 이동을 시작합니다.");
	}
}

//구현 클래스 - Helicopter
class Helicopter extends Vehicle2 {
	// 생성자
	public Helicopter(String model) {
		super("Helicopter", model);
	}

	// 이동 메서드 구현
	@Override
	void move() {
		System.out.println("Helicopter(" + super.model + ")가 이동합니다.");
	}
	// 이동 메서드 구현 2
	@Override
	void travel() {
		System.out.println("Helicopter(" + super.model + ")가 공중에서 이동을 시작합니다.");
	}
}

//테스트 클래스
public class Chap11Q2Vehicle {
	public static void main(String[] args) {
		// Jeep 객체 생성 및 이동
		Vehicle2 jeep = new Jeep("Wrangler");
		jeep.displayInfo();
		jeep.move();
		jeep.travel();

		System.out.println();

		// Helicopter 객체 생성 및 이동
		Vehicle2 helicopter = new Helicopter("Black Hawk");
		helicopter.displayInfo();
		helicopter.move();
		helicopter.travel();
	}
}
