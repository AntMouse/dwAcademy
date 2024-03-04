package chap4_scope;

public class C3Switch10 { // 11번

	public static void main(String[] args) {
		 int nLetters = 0;
		String name = "Jane";
		
		nLetters = switch (name) {	
			case "Jane":
			case "Sean": 
			case "Alan": 
			case "Paul": 
				System.out.println("There are 4 letters in : " + name);
				yield 4;
				
			case "Janet": 
			case "Susan": 
				System.out.println("There are 5 letters in : " + name);
				yield 5;
				
			case "Maaike": 
			case "Alison": 
			case "Miriam": 
				System.out.println("There are 6 letters in : " + name);
				yield 6;
				
			default:
				System.out.println("unrecognized name: " + name);
				yield -1;
		};
		System.out.println(nLetters);
	}

}
