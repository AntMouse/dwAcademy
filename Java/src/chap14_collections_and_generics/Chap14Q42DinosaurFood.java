package chap14_collections_and_generics;

public class Chap14Q42DinosaurFood {
    private String type;

    public Chap14Q42DinosaurFood(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dinosaur food type: " + type;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
