package classes_objects_enums;

import java.util.Arrays;

class Seniors {
	private int[] ages = new int[2];
	private int num;
	
	Seniors() {
		num = 2;
		ages[0] = 30;
		ages[1] = 40;
	}
	public int getNum() {
		return num;
	}
	public int[] getAges() {
		int[] copyAges = Arrays.copyOf(ages, ages.length);
		return copyAges;
	}
}

public class AdvancedEncapsulation {

	public static void main(String[] args) {
		Seniors seniors = new Seniors();
		int num = seniors.getNum();
		System.out.println(num);
		num = -100;
		num = seniors.getNum();
		System.out.println(num);

		int[] copyAges = seniors.getAges();
		System.out.println(copyAges[0] + ", " + copyAges[1]);
		copyAges[0] = -9;
		copyAges[1] = -19;
		int[] copyAges2 = seniors.getAges();
		System.out.println(copyAges2[0] + ", " + copyAges2[1]);
		
		Seniors seniors2 = new Seniors();
		int[] copyAgesplus = seniors2.getAges();
		System.out.println(copyAgesplus[0] + ", " + copyAgesplus[1]);
	}

}
