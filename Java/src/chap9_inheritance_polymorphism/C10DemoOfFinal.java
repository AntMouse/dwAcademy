package chap9_inheritance_polymorphism;

final class Earth {}
//1
//class SubEarth extends Earth {}
class Pen {
	//2
	final void write() {
	}
	//4 final abstract scribble();
}
class FounFountainPen extends Pen {
	//3
	// @Override
	// void write() {}
}

public class C10DemoOfFinal {
	//5
	final int ONE_YEAR = 1;
	void print(final String name, final int age) {
		// age = age + ONE_YEAR;
		System.out.println(name.toUpperCase());
		// name = "AlexAnder";
		//6 ONE_YEAR = 2;
	}

}
