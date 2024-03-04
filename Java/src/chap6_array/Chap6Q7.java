package chap6_array;

public class Chap6Q7 {
	public static void main(String[] args) {
		int rows = 12;
        int seatsCol = 4;
        String[][] seatChart = new String[rows][seatsCol];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsCol; j++) {
            	if (2 <= i && i <= 7) {
            		seatChart[i][j] = "X";
				} else {
					seatChart[i][j] = "O";
				}
            }
        }

        System.out.println("공원 투어 버스 의자 차트:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsCol; j++) {
                System.out.print(seatChart[i][j] + " ");
            }
            System.out.println();
        }
		

	}
}
