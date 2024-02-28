package chap11_interfance;

// 1
interface MoveableObject {}
interface Spherical {
	void doSphericalThings();
}
// 2
interface Bounceable extends MoveableObject, Spherical {
	void bounce();
}
class Volleyball implements Bounceable {
	@Override
	public void doSphericalThings() {}
	@Override
	public void bounce() {}
}
abstract class Beachball implements Bounceable {}

public class C2InterfaceInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
