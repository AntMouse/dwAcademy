package classes_objects_enums_import_try1;

import classes_objects_enums.Dog;

public class Pig1 {
	String testDog() {
		Dog d = new Dog("Shed"); // Shep -> Shed
		// d.pkgPrivate();
		return d.getDogName();
	}
}
