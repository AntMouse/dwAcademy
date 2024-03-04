package chap13_java_core_api;

public class C6HowManyObjectString {

	public static void main(String[] args) {
		String s = "The";
		s += " quick "; 
		System.out.println(s);
		// 1
		s.concat("brown fox");
		// 2
		System.out.println(s);
		// 3
		s = s.concat("brown fox");
		// 4
		System.out.println(s);
		
		String s2 = "The quick brown fox";
		System.out.println(s == s2);
		s = s.intern();
		System.out.println(s == s2);
		System.out.println(s.equals(s2));
	}

}
