package test_package;

class A3 {
	int s1 = 3;
	static int s2 = 20;
	interface B3 {
		public abstract void bcd();
		int s2 = 200;

		
		
		default void bcd2(A3 a3) {
            System.out.println(a3.s1);
        }
		static void bcd3() {
			System.out.println(A3.s2);
		}
	}
}
class C3 implements A3.B3 {
	public void bcd() {
		System.out.println("이너 인터페이스 구현 클래스 생성");
	}
}

class D3 {
	int d3 = 10;
	class E3 {
		int d4 = 20;
		void test() {
			System.out.println(D3.this.d3);
		}
	}
}

public class Test3 {

	public static void main(String[] args) {
		A3.B3 ab = new C3();
		ab.bcd();
		A3.B3.bcd3();
		ab.bcd2(new A3());
		A3 a3Instance = new A3();
        A3.B3 b3Instance = new A3.B3() {
            @Override
            public void bcd() {
                System.out.println("bcd 메서드 호출");
            }
        };
        b3Instance.bcd(); // bcd 메서드 호출
        b3Instance.bcd2(a3Instance); // 인스턴스 변수 s1 출력
        A3.B3.bcd3(); // 클래스 변수 s2 출력
	}

}
