package chap7_method;

public class C1Method7 {

	public static void main(String[] args) {
		int x = 19;
		int[] arr = {1,2};
		callByValue(x, arr);
		System.out.println(x);
		System.out.println(arr[0] + ", " + arr[1]);
		System.out.println(x);
		x = callByValue(x, arr);
		System.out.println(x);
		
	}
	
	public static int callByValue(int x, int[] arr) {
		x = -1;
		arr[0] = -1;
		return x;
	}

}
