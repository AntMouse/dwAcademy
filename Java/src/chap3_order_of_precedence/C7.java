package chap3_order_of_precedence;

public class C7 {

	public static void main(String[] args) {
		byte b1 = 3, b2 = 4;
		b1 += b2;
		System.out.println(b1); // 이 식에는 byte로 형 변환 안 해도 됨
	}

}
