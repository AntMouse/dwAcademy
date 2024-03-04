package chap5_iteration_looping;

public class Chap5Q1 {

	public static void main(String[] args) {
		String[] dinoId = new String[100];
		
		for (int i = 0; i < dinoId.length; i++) {
			dinoId[i] = "dino_" + (i + 1);		
			System.out.println("공룡 ID : " + dinoId[i]);
		}

	}

}
