package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

public class Chap14QProjectDinosaurComparator implements Comparator<Chap14Q1Dinosaur> {
    @Override
    public int compare(Chap14Q1Dinosaur d1, Chap14Q1Dinosaur d2) {
        // Assume Dinosaur class has a getSize() method
        return d1.getSize().compareTo(d2.getSize());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
