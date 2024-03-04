package chap13_java_core_api;

import java.util.ArrayList;
import java.util.List;

final class Farm {
	private final String name;
	private final int numAnimals;
	private final List<String> animals;
	
	private Farm(final String name, final int numAnimals, final List<String> animals) {
		this.name = name;
		this.numAnimals = numAnimals;
		this.animals = animals;
	}
	
	public static Farm createNewInstance(String name, int numAnimals, List<String>animals) {
		return new Farm(name, numAnimals, animals);
	}
    public String getName() { 
    	return this.name;
    }
    public int getNumAnimals() { 
    	return this.numAnimals; 
    }
    public List<String> getAnimals() {
    	return animals;
    }
    @Override
    public String toString() {
         return "Farm{" + "name=" + name + ", numAnimals=" +
                 numAnimals + ", animals=" + animals + '}';
    }

}

public class C9TestImmutable {

	public static void main(String[] args) {
		List<String> animals  = new ArrayList<>();
        animals.add("Cattle");
       
        Farm farm = Farm.createNewInstance("Small Fram", 25, animals);
        System.out.println("Created: " + farm); 
         
        String name = farm.getName();
        int numAnimals = farm.getNumAnimals();
        animals = farm.getAnimals();
        System.out.println("Retrieved: " + name + " " 
        + numAnimals + " " + animals);
        
        name = "Big Farm";
        numAnimals  = 500;
        animals.add("Sheep"); animals.add("Horses");

        System.out.println("Any change?:  " + farm);
	}

}
