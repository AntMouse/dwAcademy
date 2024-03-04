package chap4_scope;

public class Chap4Q2 {

	public static void main(String[] args) {
		String dinoFeature = "T-rex";
		
		switch (dinoFeature) {
		case "T-rex":
			System.out.println("티라노사우르스는 강력한 육식 공룡이다.");
			break;

		case "Velociraptor":
			System.out.println("벨로키랩토르는 작고 민첩한 육식 공룡이다.");
			break;

		default:
			System.out.println("해당 공룡의 정보를 찾을 수 없습니다.");
			break;
		}

	}

}
