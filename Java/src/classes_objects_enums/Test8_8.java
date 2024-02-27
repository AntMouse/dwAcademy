package classes_objects_enums;

class Dog8_8 {
	
}
class Cat8_8 {
	
}

public class Test8_8 {

	public static void main(String[] args) {
		Dog8_8 dog = new Dog8_8();
		Cat8_8 cat = new Cat8_8();
		if (dog instanceof Dog8_8) {
			System.out.println("dog referring to a Dog object");
		}
		if (cat instanceof Cat8_8) {
			System.out.println("cat referring to a Cat object");
		}
	//	if (cat instanceof Dog8_8) {
	//		System.out.println("cat referring to a Dog object");
	//	}

	}

}
