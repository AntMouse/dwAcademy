package chap13_java_core_api;

import java.util.List;
import java.util.Scanner;

public class Chap13QProject4Main {

	public static void main(String[] args) {
        Chap13QProject3DinosaurCareSystem system = new Chap13QProject3DinosaurCareSystem();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("원하는 작업을 선택하세요:");
            System.out.println("1. 공룡 추가");
            System.out.println("2. 공룡 제거");
            System.out.println("3. 공룡 목록 보기");
            System.out.println("4. 활동 기록");
            System.out.println("5. 활동 기록 제거");
            System.out.println("6. 활동 기록 보기");
            System.out.println("7. 종료");
            
            if (scanner.hasNextInt()) {
            	int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                switch (choice) {
                case 1:
                    addDinosaur(system, scanner);
                    break;
                case 2:
                	removeDinosaurMenu(system, scanner);
                    break;
                case 3:
                	getDinosaursList(system);
                    break;
                case 4:
                    logActivity(system, scanner);
                    break;
                case 5:
                    removeActivityMenu(system, scanner);
                    break;
                case 6:
                	getActivitiesList(system);
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
                }
			} else {
                System.out.println("숫자를 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }     
        }

	}
	
    private static void addDinosaur(Chap13QProject3DinosaurCareSystem system, Scanner scanner) {
        System.out.println("새로운 공룡을 추가합니다.");
        System.out.print("이름: ");
        String name = scanner.nextLine();
        
        System.out.print("종: ");
        String species = scanner.nextLine();
        
        System.out.print("건강 상태: ");
        String healthStatus = scanner.nextLine();
        
        Chap13QProject1Dinosaur newDinosaur = new Chap13QProject1Dinosaur(name, species, healthStatus);
        system.addDinosaur(newDinosaur);
        System.out.println();
    }
    
    private static void removeDinosaurMenu(Chap13QProject3DinosaurCareSystem system, Scanner scanner) {
        System.out.println("제거할 공룡의 번호를 선택하세요:");
        List<Chap13QProject1Dinosaur> dinosaurs = system.getDinosaurs();
        for (int i = 0; i < dinosaurs.size(); i++) {
            Chap13QProject1Dinosaur dinosaur = dinosaurs.get(i);
            System.out.println((i + 1) + ". 이름: " + dinosaur.getName() + ", 종: " + dinosaur.getSpecies() + ", 건강 상태: " + dinosaur.getHealthStatus());
        }
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            scanner.nextLine(); // consume newline
            system.removeDinosaur(index - 1);
        } else {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); // 입력 버퍼 비우기
        }
    }
      
    private static void getDinosaursList(Chap13QProject3DinosaurCareSystem system) {
        List<Chap13QProject1Dinosaur> dinosaurs = system.getDinosaurs();
        System.out.println("공룡 목록:");
        for (int i = 0; i < dinosaurs.size(); i++) {
            Chap13QProject1Dinosaur dinosaur = dinosaurs.get(i);
            System.out.println((i + 1) + ". 이름: " + dinosaur.getName() + ", 종: " + dinosaur.getSpecies() + ", 건강 상태: " + dinosaur.getHealthStatus());
        }
        System.out.println();
    }
    
    private static void logActivity(Chap13QProject3DinosaurCareSystem system, Scanner scanner) {
        System.out.println("활동을 기록합니다.");
        System.out.print("활동 이름: ");
        String activityName = scanner.nextLine();
        system.logActivity(activityName);
        System.out.println();
    }
    
    private static void removeActivityMenu(Chap13QProject3DinosaurCareSystem system, Scanner scanner) {
        System.out.println("제거할 활동의 번호를 선택하세요:");
        List<Chap13QProject2Activity> activities = system.getActivities();
        for (int i = 0; i < activities.size(); i++) {
            Chap13QProject2Activity activity = activities.get(i);
            System.out.println((i + 1) + ". 활동 이름: " + activity.getActivityName() + ", 날짜: " + activity.getDate() + ", 활동한 공룡: " + activity.getDinosaur().getName());
        }
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            scanner.nextLine(); // consume newline
            system.removeActivity(index - 1);
        } else {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); // 입력 버퍼 비우기
        }
    }

    private static void getActivitiesList(Chap13QProject3DinosaurCareSystem system) {
        List<Chap13QProject2Activity> activities = system.getActivities();
        System.out.println("활동 목록:");
        for (int i = 0; i < activities.size(); i++) {
            Chap13QProject2Activity activity = activities.get(i);
            System.out.println((i + 1) + ". 활동 이름: " + activity.getActivityName() + ", 날짜: " + activity.getDate() + ", 활동한 공룡: " + activity.getDinosaur().getName());
        }
        System.out.println();
    }

}
