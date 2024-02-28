package chap8_classes_objects_enums_import_try2;

class Adult {
	private String name;
	private int age;
	Adult(String name, int age) {
		if (isAgeOk(age)) {
			this.age = age;
			this.name = name;
		} else {
			this.age = -1;
			this.name = "Error";
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private boolean isAgeOk(int age) {
		return age >= 18 ? true : false;
	}

}

public class C5BasicEncapsulation {

	public static void main(String[] args) {
		Adult john = new Adult("john", 20);
		System.out.println(john.getName() + " " + john.getAge());
		// john.age = -99; private로 age를 해서 직접 변경 불가능
		
		Adult[] adultCreate = new Adult[5];
		
		String[] adultNameList = {"김", "민", "이", "홍", "주"};
		int[] adultAgeList = {20, 17, 16, 14, 13};
		
		for (int i = 0; i < adultCreate.length; i++) {
			adultCreate[i] = new Adult(adultNameList[i], adultAgeList[i]);
			System.out.println(adultCreate[i].getName() + " " + adultCreate[i].getAge());
		}
	}

}
