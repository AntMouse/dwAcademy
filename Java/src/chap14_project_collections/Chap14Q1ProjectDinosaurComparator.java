package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class Chap14Q1ProjectDinosaurComparator implements Comparator<Chap14Q1Dinosaur> {
    public enum SortingCriteria {
        AGE,
        DANGER_LEVEL,
        SIZE;
    }

    private SortingCriteria sortingCriteria;

    public Chap14Q1ProjectDinosaurComparator(SortingCriteria sortingCriteria) {
        this.sortingCriteria = sortingCriteria;
    }

    @Override
    public int compare(Chap14Q1Dinosaur d1, Chap14Q1Dinosaur d2) {
        switch (sortingCriteria) {
            case AGE:
                return Integer.compare(d1.getAge(), d2.getAge());
            case DANGER_LEVEL:
                return Integer.compare(d1.getDangerLevel(), d2.getDangerLevel());
            case SIZE:
                return compareSize(d1.getSize(), d2.getSize());
            default:
                throw new IllegalArgumentException("유효하지 않은 값입니다.");
        }
    }

    private int compareSize(String size1, String size2) {
        String[] sizes = {"매우작음", "작음", "보통", "큼", "매우큼"};
        int index1 = -1, index2 = -1;
        for (int i = 0; i < sizes.length; i++) {
            if (size1.equals(sizes[i])) {
                index1 = i;
            }
            if (size2.equals(sizes[i])) {
                index2 = i;
            }
        }
        if (index1 == -1 || index2 == -1) {
            throw new IllegalArgumentException("유효하지 않은 값입니다.");
        }
        return Integer.compare(index1, index2);
    }

	public static void main(String[] args) {
        // 공룡 객체들 생성
        Chap14Q1Dinosaur dino1 = new Chap14Q1Dinosaur("Tyrannosaurus", "Tyrannosaurus rex", "큼", 25, 10);
        Chap14Q1Dinosaur dino2 = new Chap14Q1Dinosaur("Triceratops", "Triceratops horridus", "보통", 20, 5);
        Chap14Q1Dinosaur dino3 = new Chap14Q1Dinosaur("Velociraptor", "Velociraptor mongoliensis", "작음", 10, 8);
        Chap14Q1Dinosaur dino4 = new Chap14Q1Dinosaur("Stegosaurus", "Stegosaurus stenops", "큼", 30, 3);
        Chap14Q1Dinosaur dino5 = new Chap14Q1Dinosaur("Brachiosaurus", "Brachiosaurus altithorax", "매우큼", 40, 2);

        // 공룡 객체들을 리스트에 추가
        List<Chap14Q1Dinosaur> dinosaurs = new ArrayList<>();
        dinosaurs.add(dino1);
        dinosaurs.add(dino2);
        dinosaurs.add(dino3);
        dinosaurs.add(dino4);
        dinosaurs.add(dino5);

        // 나이를 기준으로 정렬
        System.out.println("===== Age Sorting =====");
        dinosaurs.sort(new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.AGE));
        for (Chap14Q1Dinosaur dino : dinosaurs) {
            System.out.println(dino.getName() + " - " + dino.getSize());
        }

        // 위험 레벨을 기준으로 정렬
        System.out.println("\n===== Danger Level Sorting =====");
        dinosaurs.sort(new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.DANGER_LEVEL));
        for (Chap14Q1Dinosaur dino : dinosaurs) {
            System.out.println(dino.getName() + " - " + dino.getSize());
        }

        // 크기를 기준으로 정렬
        System.out.println("\n===== Size Sorting =====");
        dinosaurs.sort(new Chap14Q1ProjectDinosaurComparator(Chap14Q1ProjectDinosaurComparator.SortingCriteria.SIZE));
        for (Chap14Q1Dinosaur dino : dinosaurs) {
            System.out.println(dino.getName() + " - " + dino.getSize());
        }
	}

}