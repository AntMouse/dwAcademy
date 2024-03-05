package chap13_java_core_api;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Chap13QProject3DinosaurCareSystem {
	
    private List<Chap13QProject1Dinosaur> dinosaurs;
    private List<Chap13QProject2Activity> activities;
    private Scanner scanner;

    public Chap13QProject3DinosaurCareSystem() {
        dinosaurs = new ArrayList<>();
        activities = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        dinosaurs.add(new Chap13QProject1Dinosaur("티라노", "티라노사우루스", "좋음"));
        dinosaurs.add(new Chap13QProject1Dinosaur("트리케라", "트리케라톱스", "양호"));
        dinosaurs.add(new Chap13QProject1Dinosaur("브란티", "브란티사우루스", "보통"));
        dinosaurs.add(new Chap13QProject1Dinosaur("스테고", "스테고사우루스", "좋음"));
        dinosaurs.add(new Chap13QProject1Dinosaur("알로", "알로사우루스", "양호"));
        
        addInitialActivities();
    }
    
    // 초기값으로 활동 추가하는 메서드
    private void addInitialActivities() {
        // 초기값 활동 배열
        String[] initialActivityNames = {"급식", "산책", "수영", "휴식", "놀이"};
        
        // 공룡이 적어도 1마리 이상 있어야 함을 가정하여, 활동을 공룡 수 만큼 추가합니다.
        for (int i = 0; i < dinosaurs.size(); i++) {
            Chap13QProject1Dinosaur dinosaur = dinosaurs.get(i);
            // 활동 배열의 인덱스를 사용하여 초기값 활동을 설정합니다.
            String activityName = initialActivityNames[i % initialActivityNames.length];
            Chap13QProject2Activity activity = new Chap13QProject2Activity(activityName, LocalDate.now(), dinosaur);
            activities.add(activity);
        }
    }

    public void addDinosaur(Chap13QProject1Dinosaur dinosaur) {
        if (dinosaur != null && !dinosaur.getName().isEmpty()) {
            dinosaurs.add(dinosaur);
            System.out.println(dinosaur.getName() + "이(가) 추가되었습니다.");
        } else {
            System.out.println("유효하지 않은 공룡이거나 입력하지 않은 값이 있습니다.");
        }
    }
    
    public void removeDinosaur(int index) {
        if (0 <= index && index < dinosaurs.size()) {
            Chap13QProject1Dinosaur removedDinosaur = dinosaurs.remove(index);
            System.out.println(removedDinosaur.getName() + "이(가) 제거되었습니다.");
        } else {
            System.out.println("유효하지 않은 공룡 번호입니다.");
        }
    }

    public void logActivity(String activityName) {
    	
        if (activityName.isEmpty()) {
            System.out.println("유효하지 않은 활동이거나 입력하지 않은 값이 있습니다.");
            return;
        }

        // 공룡 선택
        while (true) {
        	LocalDate date;
        	while (true) {
                System.out.println("날짜를 선택하세요:");
                System.out.println("1. 오늘 날짜");
                System.out.println("2. 임의의 날짜");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    if (choice == 1) {
                        date = LocalDate.now();
                        break;
                    } else if (choice == 2) {
                        date = getDateFromUser();
                        break;
                    } else {
                        System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                    }
                } else {
                    System.out.println("숫자를 입력하세요.");
                    scanner.nextLine(); // 입력 버퍼 비우기
                }   	
			}

            System.out.println("활동에 참여한 공룡의 번호를 선택하세요:");
            for (int i = 0; i < dinosaurs.size(); i++) {
                System.out.println((i + 1) + ". " + dinosaurs.get(i).getName());
            }
            if (scanner.hasNextInt()) {
                int dinoIndex = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기

                if (1 <= dinoIndex && dinoIndex <= dinosaurs.size()) {
                    Chap13QProject1Dinosaur selectedDinosaur = dinosaurs.get(dinoIndex - 1);
                    Chap13QProject2Activity activity = new Chap13QProject2Activity(activityName, date, selectedDinosaur);
                    activities.add(activity);
                    System.out.println(activityName + "이(가) 기록되었습니다.");
                    break;
                } else {
                    System.out.println("유효한 공룡 번호를 입력하세요.");
                }
            } else {
                System.out.println("숫자를 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }
        }
    }
    
    private LocalDate getDateFromUser() {
        System.out.println("날짜를 입력하세요 (예: 2024-03-07):");
        String userInput = scanner.nextLine();
        try {
            return LocalDate.parse(userInput, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("올바른 날짜 형식이 아닙니다. 오늘 날짜로 설정합니다.");
            return LocalDate.now();
        }
    }
    
    public void removeActivity(int index) {
        if (0 <= index && index < activities.size()) {
            Chap13QProject2Activity removedActivity = activities.remove(index);
            System.out.println(removedActivity.getActivityName() + "이(가) 제거되었습니다.");
        } else {
            System.out.println("유효하지 않은 활동 번호입니다.");
        }
    }
    
    // Getter 메서드
    public List<Chap13QProject1Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public List<Chap13QProject2Activity> getActivities() {
        return activities;
    }


	public static void main(String[] args) {
		Chap13QProject3DinosaurCareSystem system = new Chap13QProject3DinosaurCareSystem();

        // 테스트를 위해 몇 가지 공룡을 미리 추가
        system.addDinosaur(new Chap13QProject1Dinosaur("티라노", "티라노사우루스", "좋음"));
        system.addDinosaur(new Chap13QProject1Dinosaur("트리케라", "트리케라톱스", "양호"));
        system.addDinosaur(new Chap13QProject1Dinosaur("브란티", "브란티사우루스", "보통"));

        // 몇 가지 활동 기록
        system.logActivity("급식");
        system.logActivity("산책");

        // 공룡 목록 가져오기
        List<Chap13QProject1Dinosaur> dinosaurs = system.getDinosaurs();
        System.out.println("공룡 목록:");
        for (Chap13QProject1Dinosaur dinosaur : dinosaurs) {
            System.out.println(dinosaur.getName());
        }

        // 활동 목록 가져오기
        List<Chap13QProject2Activity> activities = system.getActivities();
        System.out.println("활동 목록:");
        for (Chap13QProject2Activity activity : activities) {
            System.out.println(activity.getActivityName());
        }
	}

}
