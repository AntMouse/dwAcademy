package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.util.*;

public class Chap14QProjectDinosaurCareSystem {
    private Set<Chap14Q1Dinosaur> dinosaurs;
    private List<Chap14QProjectActivity> activities;

    public Chap14QProjectDinosaurCareSystem() {
        dinosaurs = new HashSet<>();
        activities = new ArrayList<>();
    }
    
    public void addDinosaur(Chap14Q1Dinosaur dinosaur) {
        dinosaurs.add(dinosaur);
    }

    public void logActivity(Chap14QProjectActivity activity) {
        activities.add(activity);
    }

    public List<Chap14Q1Dinosaur> sortDinosaursByAge() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14QProjectDinosaurComparator(Chap14QProjectDinosaurComparator.SortingCriteria.AGE));
        return sortedDinosaurs;
    }

    public List<Chap14Q1Dinosaur> sortDinosaursByDangerLevel() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14QProjectDinosaurComparator(Chap14QProjectDinosaurComparator.SortingCriteria.DANGER_LEVEL));
        return sortedDinosaurs;
    }

    public List<Chap14Q1Dinosaur> sortDinosaursBySize() {
        List<Chap14Q1Dinosaur> sortedDinosaurs = new ArrayList<>(dinosaurs);
        Collections.sort(sortedDinosaurs, new Chap14QProjectDinosaurComparator(Chap14QProjectDinosaurComparator.SortingCriteria.SIZE));
        return sortedDinosaurs;
    }

	public static void main(String[] args) {
	    Chap14QProjectDinosaurCareSystem careSystem = new Chap14QProjectDinosaurCareSystem();

	    // 공룡 객체 생성
	    Chap14Q1Dinosaur dino1 = new Chap14Q1Dinosaur("Tyrannosaurus", "Tyrannosaurus rex", "큼", 25, 10);
	    Chap14Q1Dinosaur dino2 = new Chap14Q1Dinosaur("Triceratops", "Triceratops horridus", "보통", 20, 5);
	    Chap14Q1Dinosaur dino3 = new Chap14Q1Dinosaur("Velociraptor", "Velociraptor mongoliensis", "작음", 10, 8);

	    // 공룡을 시스템에 추가
	    careSystem.addDinosaur(dino1);
	    careSystem.addDinosaur(dino2);
	    careSystem.addDinosaur(dino3);

	    // 나이를 기준으로 정렬된 공룡 리스트를 가져옴
	    List<Chap14Q1Dinosaur> sortedByAge = careSystem.sortDinosaursByAge();

	    // 정렬된 리스트 출력
	    System.out.println("===== Sorted by Age =====");
	    for (Chap14Q1Dinosaur dinosaur : sortedByAge) {
	        System.out.println(dinosaur.getName() + " - Age: " + dinosaur.getAge());
	    }

	}

}
