package chap13_java_core_api;

public class C5StringTest {

	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1 == s2);
		String s3 = new String("abc");
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		// 1
		s3 = s3.intern();
		// 2
		System.out.println(s1 == s3);
	}

}
