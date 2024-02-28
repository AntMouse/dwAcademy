package chap8_classes_objects_enums;

public class Chap8Q1Dinosaur {
	private String dinoName;
	private int dinoAge;
	private String dinoSpecies;
	private String dinoStatus;
	private int dinoLocationNumber;
	private static boolean[] availableLocations = new boolean[999];
	
	public Chap8Q1Dinosaur(String dinoName, int dinoAge, String dinoSpecies, String dinoStatus) {
		this.dinoName = dinoName;
		this.dinoAge = dinoAge;
		this.dinoSpecies = dinoSpecies;
		this.dinoStatus = dinoStatus;		
		
		if (findAvailableLocation() != -1) {
			availableLocations[findAvailableLocation()] = true;
			dinoLocationNumber = findAvailableLocation();
		}
	}
	
    // 사용 가능한 우리 번호를 찾는 메서드
    private int findAvailableLocation() {
        for (int i = 0; i < availableLocations.length; i++) {
            if (!availableLocations[i]) {
                return i;
            }
        }
        return -1; // 사용 가능한 우리 번호가 없는 경우
    }

    // 공룡 삭제 시 해당 우리 번호를 다시 사용 가능하도록 설정하는 메서드
    public static void deleteDinosaurLocation(int locationNumber) {
        if (0 < locationNumber && locationNumber <= availableLocations.length) {
            availableLocations[locationNumber] = false;
        } else {
            System.out.println("잘못된 우리 번호입니다.");
        }
    }
	
	public String getDinoName() {
		return dinoName;
	}
	public int getDinoAge() {
		return dinoAge;
	}	
	public String getDinoSpecies() {
		return dinoSpecies;
	}
	public String getDinoStatus() {
		return dinoStatus;
	}
	public int getDinoLocationNumber() {
		return dinoLocationNumber;
	}
	
	public void changeDinoName(String dinoName) {
		this.dinoName = dinoName;
	}	
	public void changeDinoAge(int dinoAge) {
		this.dinoAge = dinoAge;
	}	
	public void changeDinoSpecies(String dinoSpecies) {
		this.dinoSpecies = dinoSpecies;
	}
	public void changeDinoStatus(String dinoStatus) {
		this.dinoStatus = dinoStatus;
	}
	public void changeDinoLocationNumber(int dinoLocationNumber) {
		this.dinoLocationNumber = dinoLocationNumber;
	}
	
	public static void main(String[] args) {
		

	}

}
