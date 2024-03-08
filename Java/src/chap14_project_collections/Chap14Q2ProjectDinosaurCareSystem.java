package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;
import chap13_java_core_api.Chap13QProject1Dinosaur;
import chap13_java_core_api.Chap13QProject2Activity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

public class Chap14Q2ProjectDinosaurCareSystem {
	Scanner scanner = new Scanner(System.in);
    private Set<Chap14Q1Dinosaur> dinosaurs;
    private List<Chap14Q3ProjectActivity> activities;

    public Chap14Q2ProjectDinosaurCareSystem() {
        dinosaurs = new HashSet<>();
        activities = new ArrayList<>();
    }
    
    public void addDinosaur(Chap14Q1Dinosaur dinosaur) {
        dinosaurs.add(dinosaur);
        System.out.println(dinosaur.getName() + "이(가) 추가되었습니다.");
    }

    public List<Chap14Q1Dinosaur> getDinosaurs() {
        List<Chap14Q1Dinosaur> dinosaurList = new ArrayList<>(dinosaurs);
        return dinosaurList;
    }

    public void logActivity(String activityName) {
    	
        if (activityName.isEmpty()) {
            System.out.println("유효하지 않은 활동이거나 입력하지 않은 값이 있습니다.");
            return;
        }

        // 공룡 선택
        LocalDate date;
        while (true) {
        	System.out.println("날짜를 선택하세요:");
            System.out.println("1. 오늘 날짜");
            System.out.println("2. 임의의 날짜");
            if (scanner.hasNextInt()) {
            	int choice = scanner.nextInt();
            	scanner.nextLine(); // consume newline                   
            	if (choice == 1) {
            		date = LocalDate.now();
            		break;
            	} else if (choice == 2) {
            		date = getDateFromUser();
            		break;
            	} else {
            		System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            	}
            } else {
            	System.out.println("숫자를 입력하세요.");
            	scanner.nextLine(); // 입력 버퍼 비우기
            }   	
        }
        
        List<Chap14Q1Dinosaur> sortedDinosaurs = sortDinosaursByAge();
        
        while (true) {
            System.out.println("활동에 참여한 공룡의 이름을 선택하세요:");
            int count = 1;
            for (Chap14Q1Dinosaur dinosaur : sortedDinosaurs) {
                System.out.println(count + ". " + dinosaur.getName());
                System.out.println("=============================");
                count++;
            }
            String selectedDinoName = scanner.nextLine();
            // 입력한 공룡 이름이 존재하는지 찾기
            boolean found = false;
            for (Chap14Q1Dinosaur dinosaur : dinosaurs) {
                if (dinosaur.getName().equalsIgnoreCase(selectedDinoName)) {
                	
                	
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("해당 이름의 공룡이 존재하지 않습니다.");
                break;
            } else {
                System.out.println("활동이 추가되었습니다.");
            }
        }       
    }
    
    private LocalDate getDateFromUser() {
        System.out.println("날짜를 입력하세요 (예: 2024-03-07):");
        String userInput = scanner.nextLine();
        try {
            return LocalDate.parse(userInput, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("올바른 날짜 형식이 아닙니다. 오늘 날짜로 설정합니다.");
            return LocalDate.now();
        }
    }
      
    public void logActivity(Chap14Q3ProjectActivity activity) {
        activities.add(activity);
    }

    public List<Chap14Q1Dinosaur> sortDinosaursByAge() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.AGE));
        return sortedDinosaurs;
    }

    public List<Chap14Q1Dinosaur> sortDinosaursByDangerLevel() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.DANGER_LEVEL));
        return sortedDinosaurs;
    }

    public List<Chap14Q1Dinosaur> sortDinosaursBySize() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.SIZE));
        return sortedDinosaurs;
    }
    
    
    

	public static void main(String[] args) {
	    Chap14Q2ProjectDinosaurCareSystem careSystem = new Chap14Q2ProjectDinosaurCareSystem();

	    // 공룡 객체 생성
	    Chap14Q1Dinosaur dino1 = new Chap14Q1Dinosaur("Tyrannosaurus", "Tyrannosaurus rex", "큼", 25, 10);
	    Chap14Q1Dinosaur dino2 = new Chap14Q1Dinosaur("Triceratops", "Triceratops horridus", "보통", 20, 5);
	    Chap14Q1Dinosaur dino3 = new Chap14Q1Dinosaur("Velociraptor", "Velociraptor mongoliensis", "작음", 10, 8);

	    // 공룡을 시스템에 추가
	    careSystem.addDinosaur(dino1);
	    careSystem.addDinosaur(dino2);
	    careSystem.addDinosaur(dino3);

	    // 나이를 기준으로 정렬된 공룡 리스트를 가져옴
	    List<Chap14Q1Dinosaur> sortedByAge = careSystem.sortDinosaursBySize();

	    // 정렬된 리스트 출력
	    System.out.println("===== Sorted by Age =====");
	    for (Chap14Q1Dinosaur dinosaur : sortedByAge) {
	        System.out.println(dinosaur.getName() + " - Age: " + dinosaur.getSize());
	    }

	}

}
