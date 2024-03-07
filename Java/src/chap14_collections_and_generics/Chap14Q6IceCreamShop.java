package chap14_collections_and_generics;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Chap14Q6IceCreamShop {
	
    private static final Map<String, Integer> iceCreamSpoons = new HashMap<>();
    static {
        iceCreamSpoons.put("바닐라", 1);
        iceCreamSpoons.put("초콜릿", 2);
        iceCreamSpoons.put("딸기", 5);
        iceCreamSpoons.put("민트초콜릿칩", 4);
        iceCreamSpoons.put("카라멜", 3);
    }

    private static final Map<String, List<String>> iceCreamDescriptions = new HashMap<>();
    static {
        iceCreamDescriptions.put("바닐라", new ArrayList<>(Arrays.asList("달콤한맛", "시원한맛", "신맛")));
        iceCreamDescriptions.put("초콜릿", new ArrayList<>(Arrays.asList("진한맛", "부드러운맛", "고소한맛")));
        iceCreamDescriptions.put("딸기", new ArrayList<>(Arrays.asList("상큼한맛", "달콤한맛", "싱그러운맛")));
        iceCreamDescriptions.put("민트초콜릿칩", new ArrayList<>(Arrays.asList("신선한맛", "부드러운맛", "상쾌한맛")));
        iceCreamDescriptions.put("카라멜", new ArrayList<>(Arrays.asList("달콤한맛", "부드러운맛", "풍부한맛")));
    }
    
    private static String spoonsNeededString(Map<String, Integer> spoonsNeeded, Map<String, Integer> iceCreamOrders) {
        StringBuilder sb = new StringBuilder("수저 개수: ");
        for (Map.Entry<String, Integer> entry : spoonsNeeded.entrySet()) {
            String flavor = entry.getKey();
            int count = entry.getValue();
            int spoonsPerIceCream = iceCreamSpoons.get(flavor);
            int orders = iceCreamOrders.getOrDefault(flavor, 0);
            sb.append(flavor).append("=").append(count).append("(").append(spoonsPerIceCream).append("*").append(orders).append(") ");
        }
        return sb.toString();
	}
    
	private static String calculatePreparationTime(Map<String, Integer> iceCreamOrders, LocalTime currentTime) {
	    // 아이스크림 한 개 준비하는 데 기본적으로 소요되는 시간(분)
	    final int BASE_PREPARATION_TIME_PER_ICE_CREAM = 10;

	    // 아이스크림 한 개를 추가로 주문할 때마다 증가되는 준비시간(분)
	    final int ADDITIONAL_PREPARATION_TIME_PER_ICE_CREAM = 1;

	    int totalOrderedIceCreams = 0;
	    for (int orders : iceCreamOrders.values()) {
	        totalOrderedIceCreams += orders;
	    }

	    /*
	    전체 주문된 아이스크림의 수에 따라 총 준비시간 계산.
	    아이스크림 1개는 기본 준비시간이 10분이므로, -1을 해준다.
	    1개를 주문하면 10분이 나와야 하는데 아래 계산식 대로면 11분이 나오기 때문이다.
	    */
	    int preparationTime = BASE_PREPARATION_TIME_PER_ICE_CREAM + (totalOrderedIceCreams - 1) * ADDITIONAL_PREPARATION_TIME_PER_ICE_CREAM;

	    // 현재 시간에 총 준비시간을 더하여 완료 시간 계산
	    LocalTime completionTime = currentTime.plusMinutes(preparationTime);

	    // 완료 시간을 문자열로 변환하여 반환
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    return completionTime.format(formatter);
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

	    // 현재 시간을 얻습니다.
	    LocalTime currentTime = LocalTime.now();

	    // 손님이 주문한 아이스크림과 필요한 수저의 개수를 출력합니다.
	    System.out.println("주문하신 아이스크림: " + orderedIceCreams);
	    System.out.println(spoonsNeededString(spoonsNeeded, iceCreamOrders));

	    // 주문한 아이스크림이 준비되는 시간을 출력합니다.
	    System.out.println("주문한 아이스크림이 나오는 예정 시간: " + calculatePreparationTime(iceCreamOrders, currentTime));
	}

}
