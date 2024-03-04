package chap6_array;

public class Chap6Q4 {

	public static void main(String[] args) {
		String[][] employeeNameList = {
			{"이재일1", "이재일2", "이재일3", "이재일4", "이재일5"},
			{"김주호1", "김주호2", "김주호3", "김주호4", "김주호5"},
			{"민주호1", "민주호2", "민주호3", "민주호4", "민주호5"},
			{"한주호1", "한주호2", "한주호3", "한주호4", "한주호5"},
			{"참주호1", "참주호2", "참주호3", "참주호4", "참주호5"}
		};
		
		for (String[] strings1 : employeeNameList) {
			for (String strings2 : strings1) {
				System.out.print(strings2 + " ");
			}
			System.out.println();
		}
		
	}

}
