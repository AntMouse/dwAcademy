package chap13_java_core_api;

public class Chap13QProject1Dinosaur {
	
    private String name;
    private String species;
    private String healthStatus;

    public Chap13QProject1Dinosaur(String name, String species, String healthStatus) {
        this.name = name;
        this.species = species;
        this.healthStatus = healthStatus;
    }

    // 각 속성의 Getter 및 Setter 메서드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

	public static void main(String[] args) {
		

	}

}
