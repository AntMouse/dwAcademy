package chap6_array;

public class Chap6Q1 {

	public static void main(String[] args) {
		String[] dinoTypeList = {
		"T-Rex", "Brachiodaurus", "Stegosaurus", "Ankylosaurus", "Brachiosaurus"
		};
		
		System.out.println("공원에 있는 공룡 목록");
		for (String outputStr : dinoTypeList) {
			System.out.println(outputStr);
		}

	}

}
