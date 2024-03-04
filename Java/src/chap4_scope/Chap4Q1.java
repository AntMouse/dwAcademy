package chap4_scope;

public class Chap4Q1 {

	public static void main(String[] args) {
		boolean isCarnivore = true;
		
		if (isCarnivore) {
			System.out.println("육식 공룡입니다.");
		} 
		
		if (!isCarnivore) {
			System.out.println("초식 공룡입니다.");
		}
		

	}

}
