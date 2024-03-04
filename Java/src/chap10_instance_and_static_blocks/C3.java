package chap10_instance_and_static_blocks;

class Machine2 {
	void on() { System.out.println("Machine2::on()"); }
}
class Tractor2 extends Machine2 {
	@Override
	void on() { System.out.println("Tractor2::on()"); }
	void drive() { System.out.println("Tractor2::drive()"); }
}

public class C3 {
	
	public static void doAction(Machine2 machine2) {
		// machine2.on();
		// machine2.drive();
		// ((Tractor2)machine2).drive();
		if (machine2 instanceof Tractor2 t) {
			t.drive();
			t.on();
		}
	}

	public static void main(String[] args) {
		doAction(new Machine2()); // 실행 안 됨
		doAction(new Tractor2()); // 실행
	}

}
