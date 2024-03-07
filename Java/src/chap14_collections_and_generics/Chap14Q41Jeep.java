package chap14_collections_and_generics;

public class Chap14Q41Jeep {
    private String model;
    private String color;

    public Chap14Q41Jeep(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Jeep model: " + model + ", color: " + color;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
