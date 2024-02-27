package classes_objects_enums;

public record Person20(String name, Integer age) {
	public person20 {
		if (age < 18) {
			name = "Error";
			age = -1;
		}
	}
}

class PersonTest {
	public static void main(String[] args) {
		person20 p1 = new Person20("Joe Bloggs", 20);
	}
}

public class Test8_12 {

	public static void main(String[] args) {
		

	}

}
