package chap13_java_core_api;

public class C8StringVersusStringBuilder {

	public static void main(String[] args) {
		String s = "Hi";
		StringBuilder sb = new StringBuilder("Hi");
		// 1
		whatHappens(s, sb);
		System.out.println("main : " + s);
		System.out.println("main : " + sb);
		s = s.concat("there!");
		System.out.println(s);
	}
	
	public static void whatHappens(String s, StringBuilder sb) {
		s = s.concat("there!");
		sb.append("there!");
		System.out.println("whatHappens : " + s);
		System.out.println("whatHappens : " + sb);
	}

}
