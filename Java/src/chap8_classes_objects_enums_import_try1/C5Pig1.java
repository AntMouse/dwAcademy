package chap8_classes_objects_enums_import_try1;

import chap8_classes_objects_enums.C5Dog;

public class C5Pig1 {
	String testDog() {
		C5Dog d = new C5Dog("Shed"); // Shep -> Shed
		// d.pkgPrivate();
		return d.getDogName();
	}
}
