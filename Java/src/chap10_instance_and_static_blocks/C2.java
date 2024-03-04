package chap10_instance_and_static_blocks;

class Machine {
	void on() { System.out.println("Machine::on()"); }
}
class Tractor extends Machine {
	@Override
	void on() { System.out.println("Tractor::on()"); }
	void drive() { System.out.println("Tractor::drive()"); }
}

public class C2 {
	
	public static void doAction(Machine machine) {
		machine.on();
	}

	public static void main(String[] args) {
		Machine mt = new Tractor();
		doAction(mt);
		doAction(new Tractor());

	}

}
