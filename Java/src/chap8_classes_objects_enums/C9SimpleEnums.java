package chap8_classes_objects_enums;

enum Water {
	STILL, SPARKLING;
}

public class C9SimpleEnums {

	public static void main(String[] args) {
		// Water stillWater = new Water();
		// Water stillWater = Water.EXTRA_SPARKLING;
		Water stillWater = Water.STILL;
		System.out.println(stillWater == Water.STILL);
		System.out.println(stillWater.equals(Water.STILL));
		
		switch (stillWater) {
		case STILL:
			System.out.println("Still water");
			break;
		// case Water.STILL:
		// case 0:
		default:
			break;
		}
		
		// if (Water.STILL == 0) {
		// }
		Water sparklingWater = Water.valueOf("SPARKLING");
		System.out.println(sparklingWater);
		
		for (Water water : Water.values()) {
			// Ordinal value of : 0 is STILL
			// Ordinal value of : 1 is SPARKLING
			System.out.println("Ordinal value of : " + water.ordinal() + " is " + 
			water.name());
		}
	}

}
