package chap5_iteration_looping;

import java.util.Scanner;

public class Chap5Q5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // 티켓 수량
        int totalTickets = 5000;

        // 판매된 티켓 수량
        int soldTickets = 0;

        // 판매 과정 시뮬레이션
        while (true) {
            System.out.println("현재 남은 티켓 수량: " + totalTickets);

            // 티켓이 모두 팔린 경우
            if (totalTickets == 0) {
                System.out.println("티켓이 모두 팔렸습니다.");
                break;
            }

            // 티켓을 구매할 고객 수 입력
            System.out.print("구매할 티켓 수량을 입력하세요: ");
            int numTicketsToBuy = sc.nextInt();

            // 입력받은 티켓 수량이 재고보다 많은지 확인
            if (numTicketsToBuy > totalTickets) {
                System.out.println("재고가 부족합니다. 현재 남은 티켓 수량보다 적게 입력하세요.");
                continue;
            }

            // 티켓 판매 처리
            totalTickets -= numTicketsToBuy;
            soldTickets += numTicketsToBuy;

            System.out.println(numTicketsToBuy + "장의 티켓을 판매하였습니다.");
        }

        // 종료 메시지 출력
        System.out.println("티켓 판매가 종료되었습니다.");
        System.out.println("총 " + soldTickets + "장의 티켓이 판매되었습니다.");
        sc.close();

	}

}
