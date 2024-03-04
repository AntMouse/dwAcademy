package chap13_java_core_api;

import java.util.ArrayList;
import java.util.List;

final class Farm2 {
	private final String name;
	private final int numAnimals;
	private final List<String> animals;
	
	private Farm2(final String name, final int numAnimals, final List<String> animals) {
		this.name = name;
		this.numAnimals = numAnimals;
		this.animals = new ArrayList<String>(animals);
	}
	
	public static Farm2 createNewInstance(String name, int numAnimals, List<String>animals) {
		return new Farm2(name, numAnimals, animals);
	}
    public String getName() { 
    	return this.name;
    }
    public int getNumAnimals() { 
    	return this.numAnimals; 
    }
    public List<String> getAnimals() {
    	return new ArrayList<String>(animals);
    }
    @Override
    public String toString() {
         return "Farm{" + "name=" + name + ", numAnimals=" +
                 numAnimals + ", animals=" + animals + '}';
    }

}

public class C10TestImmutable2 {

	public static void main(String[] args) {
		List<String> animals  = new ArrayList<>();
        animals.add("Cattle");
       
        Farm2 farm2 = Farm2.createNewInstance("Small Fram", 25, animals);
        System.out.println("Created: " + farm2); 
         
        String name = farm2.getName();
        int numAnimals = farm2.getNumAnimals();
        animals = farm2.getAnimals();
        System.out.println("Retrieved: " + name + " " 
        + numAnimals + " " + animals);
        
        name = "Big Farm";
        numAnimals  = 500;
        animals.add("Sheep"); animals.add("Horses");

        System.out.println("Any change?:  " + farm2);
        System.out.println(animals);
	}

}
