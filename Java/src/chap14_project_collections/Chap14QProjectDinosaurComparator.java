package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.util.Comparator;

public class Chap14QProjectDinosaurComparator implements Comparator<Chap14Q1Dinosaur> {
    public enum SortingCriteria {
        AGE,
        DANGER_LEVEL,
        SIZE
    }

    private SortingCriteria sortingCriteria;

    public Chap14QProjectDinosaurComparator(SortingCriteria sortingCriteria) {
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
                throw new IllegalArgumentException("Invalid sorting criteria");
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
            throw new IllegalArgumentException("Invalid size");
        }
        return Integer.compare(index1, index2);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}