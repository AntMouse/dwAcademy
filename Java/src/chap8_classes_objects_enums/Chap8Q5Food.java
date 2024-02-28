package chap8_classes_objects_enums;

public class Chap8Q5Food {
	private String foodName;
	private String foodNutritionalValue;
	private int foodCost;
	
	public Chap8Q5Food(String foodName, String foodNutritionalValue, int foodCost) {
		this.foodName = foodName;
		this.foodNutritionalValue = foodNutritionalValue;
		this.foodCost = foodCost;
	}
	
	public String getFoodName() {
		return foodName;
	}
	public String getFoodNutritionalValue() {
		return foodNutritionalValue;
	}	
	public int getFoodCost() {
		return foodCost;
	}
	
	public void changeFoodName(String foodName) {
		this.foodName = foodName;
	}	
	public void changeFoodNutritionalValue(String foodNutritionalValue) {
		this.foodNutritionalValue = foodNutritionalValue;
	}	
	public void changeFoodCost(int foodCost) {
		this.foodCost = foodCost;
	}
	
	public static void main(String[] args) {
		Chap8Q5Food food = new Chap8Q5Food("소고기", "높음", 100000);
		
		System.out.println("음식 이름 : " + food.getFoodName());
		System.out.println("영양가 : " + food.getFoodNutritionalValue());
		System.out.println("음식 가격 : " + food.getFoodCost());
	}

}
