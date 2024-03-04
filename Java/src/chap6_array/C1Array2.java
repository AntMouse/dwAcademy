package chap6_array;

public class C1Array2 {

	public static void main(String[] args) {
		double[] results = {7.0, 8.6, 9.0};
		double total = 0;
		
		for (int i = 0; i < results.length; i++) {
			total += results[i];
		}
		
		double average = total / results.length;
		System.out.println(average);
	}

}
