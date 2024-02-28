package chap8_classes_objects_enums_import_try1;

import chap8_classes_objects_enums.C5Dog;

public class C5pig2 {

	public static void main(String[] args) {
		C5Dog d1 = new C5Dog("Rex");
		C5Pig1 d2 = new C5Pig1();
		
		System.out.println(d1.getDogName());
		System.out.println(d2.testDog());
		//Rex
		//Shed
		
	}

}
