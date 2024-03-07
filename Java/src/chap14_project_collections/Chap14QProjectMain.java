package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.util.List;
import java.util.Scanner;

public class Chap14QProjectMain {

	public static void main(String[] args) {
		Chap14QProjectDinosaurCareSystem system = new Chap14QProjectDinosaurCareSystem();
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
            
            while (true) {
				System.out.print("메뉴를 다시 불러오기는 'M', 종료는 'E'를 입력 : ");
				String menuRestart = scanner.nextLine();
				if (menuRestart.equalsIgnoreCase("m")) {
					System.out.println();
					break;
				} else if (menuRestart.equalsIgnoreCase("e")) {
					System.out.print("프로그램을 종료합니다.");
					return;
				} else {
					System.out.println("잘못된 값입니다. 다시 입력해주세요.");
				}
			}
        }

	}
	
    private static void addDinosaur(Chap14QProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("새로운 공룡을 추가합니다.");
        System.out.print("이름: ");
        String name = scanner.nextLine();
        
        System.out.print("종: ");
        String species = scanner.nextLine();
        
        System.out.print("건강 상태: ");
        String healthStatus = scanner.nextLine();
        
        Chap14Q1Dinosaur newDinosaur = new Chap14Q1Dinosaur(name, species, healthStatus);
        system.addDinosaur(newDinosaur);
        System.out.println();
    }
    
    private static void removeDinosaurMenu(Chap14QProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("제거할 공룡의 번호를 선택하세요:");
        getDinosaursList(system);
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            scanner.nextLine(); // consume newline
            system.removeDinosaur(index - 1);
        } else {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); // 입력 버퍼 비우기
        }
    }
      
    private static void getDinosaursList(Chap14QProjectDinosaurCareSystem system) {
        List<Chap14Q1Dinosaur> dinosaurs = system.getDinosaurs();
        System.out.println("공룡 목록:");
        for (int i = 0; i < dinosaurs.size(); i++) {
        	Chap14Q1Dinosaur dinosaur = dinosaurs.get(i);
            System.out.println((i + 1) + ". 이름: " + dinosaur.getName() + ", 종: " + dinosaur.getSpecies() + ", 건강 상태: " + dinosaur.getHealthStatus());
        }
        System.out.println();
    }
    
    private static void logActivity(Chap14QProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("활동을 기록합니다.");
        System.out.print("활동 이름: ");
        String activityName = scanner.nextLine();
        system.logActivity(activityName);
        System.out.println();
    }
    
    private static void removeActivityMenu(Chap14QProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("제거할 활동의 번호를 선택하세요:");
        getActivitiesList(system);
        if (scanner.hasNextInt()) {
            int index = scanner.nextInt();
            scanner.nextLine(); // consume newline
            system.removeActivity(index - 1);
        } else {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); // 입력 버퍼 비우기
        }
    }

    private static void getActivitiesList(Chap14QProjectDinosaurCareSystem system) {
        List<Chap14QProjectActivity> activities = system.getActivities();
        System.out.println("활동 목록:");
        for (int i = 0; i < activities.size(); i++) {
        	Chap14QProjectActivity activity = activities.get(i);
            System.out.println((i + 1) + ". 활동 이름: " + activity.getActivityName() + ", 날짜: " + activity.getDate() + ", 활동한 공룡: " + activity.getDinosaur().getName());
        }
        System.out.println();
    }

}
