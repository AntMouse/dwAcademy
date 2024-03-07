package test_package;

import java.util.*;

public class Chap14Q6IceCreamShop {
	
    // 아이스크림과 수저의 양을 나타내는 맵
    private static final Map<String, Integer> iceCreamSpoons = new HashMap<>();
    static {
        iceCreamSpoons.put("바닐라", 1);
        iceCreamSpoons.put("초콜릿", 2);
        iceCreamSpoons.put("딸기", 5);
        iceCreamSpoons.put("민트초콜릿칩", 4);
        iceCreamSpoons.put("카라멜", 3);
    }

    // 아이스크림 종류와 설명을 나타내는 맵
    private static final Map<String, List<String>> iceCreamDescriptions = new HashMap<>();
    static {
        iceCreamDescriptions.put("바닐라", new ArrayList<>(Arrays.asList("달콤한맛", "시원한맛", "신맛")));
        iceCreamDescriptions.put("초콜릿", new ArrayList<>(Arrays.asList("진한맛", "부드러운맛", "고소한맛")));
        iceCreamDescriptions.put("딸기", new ArrayList<>(Arrays.asList("상큼한맛", "달콤한맛", "싱그러운맛")));
        iceCreamDescriptions.put("민트초콜릿칩", new ArrayList<>(Arrays.asList("신선한맛", "부드러운맛", "상쾌한맛")));
        iceCreamDescriptions.put("카라멜", new ArrayList<>(Arrays.asList("달콤한맛", "부드러운맛", "풍부한맛")));
    }
    
    // 수저 개수를 문자열로 반환하는 메서드
    private static String spoonsNeededString(Map<String, Integer> spoonsNeeded, Map<String, Integer> iceCreamOrders) {
        StringBuilder sb = new StringBuilder("수저 개수: ");
        for (Map.Entry<String, Integer> entry : spoonsNeeded.entrySet()) {
            String flavor = entry.getKey();
            int count = entry.getValue();
            int spoonsPerIceCream = iceCreamSpoons.get(flavor); // 아이스크림 한 개당 필요한 수저 개수
            int orders = iceCreamOrders.getOrDefault(flavor, 0); // 해당 아이스크림 종류의 주문 횟수
            sb.append(flavor).append("=").append(count).append("(").append(spoonsPerIceCream).append("*").append(orders).append(") ");
        }
        return sb.toString();
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 손님이 주문한 아이스크림의 개수를 물어봅니다.
        System.out.print("주문하실 아이스크림 개수를 입력하세요: ");
        int iceCreamCount = scanner.nextInt();

        // 손님의 아이스크림 주문을 처리합니다.
        List<String> orderedIceCreams = new ArrayList<>();
        Map<String, Integer> spoonsNeeded = new HashMap<>();
        Map<String, Integer> iceCreamOrders = new HashMap<>(); // 아이스크림 종류별 주문 횟수를 저장하는 맵
        for (int i = 0; i < iceCreamCount; i++) {
            // 아이스크림 종류를 물어봅니다.
            System.out.print("아이스크림 종류를 입력하세요 (바닐라, 초콜릿, 딸기, 민트초콜릿칩, 카라멜): ");
            String flavor = scanner.next();

            // 아이스크림을 먹기 위해 필요한 수저의 개수를 계산합니다.
            spoonsNeeded.put(flavor, spoonsNeeded.getOrDefault(flavor, 0) + iceCreamSpoons.get(flavor));

            // 해당 아이스크림의 주문 횟수를 증가시킵니다.
            iceCreamOrders.put(flavor, iceCreamOrders.getOrDefault(flavor, 0) + 1);

            // 아이스크림 설명을 출력합니다.
            System.out.println("아이스크림 설명:");
            List<String> descriptions = iceCreamDescriptions.get(flavor);
            for (int j = 0; j < descriptions.size(); j++) {
                System.out.println((j+1) + ". " + descriptions.get(j));
            }

            // 사용자가 원하는 맛을 선택합니다.
            System.out.print("원하는 맛의 번호를 선택하세요: ");
            int selectedFlavorIndex = scanner.nextInt();
            String selectedFlavor = descriptions.get(selectedFlavorIndex - 1);

            // 선택한 맛을 아이스크림 종류에 추가하여 출력합니다.
            String orderedIceCream = flavor + "(" + selectedFlavor + ")";
            orderedIceCreams.add(orderedIceCream);
        }
        

        // 손님이 주문한 아이스크림과 필요한 수저의 개수를 출력합니다.
        System.out.println("주문하신 아이스크림: " + orderedIceCreams);
        System.out.println(spoonsNeededString(spoonsNeeded, iceCreamOrders));
    }

}
