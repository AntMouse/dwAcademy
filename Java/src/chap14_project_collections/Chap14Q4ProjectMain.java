package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Chap14Q4ProjectMain {

	public static void main(String[] args) {
		Chap14Q2ProjectDinosaurCareSystem system = new Chap14Q2ProjectDinosaurCareSystem();
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
	
    private static void addDinosaur(Chap14Q2ProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("새로운 공룡을 추가합니다.");
        System.out.print("이름: ");
        String name = scanner.nextLine();
        
        System.out.print("종: ");
        String species = scanner.nextLine();
        
        System.out.print("크기: ");
        String size = scanner.nextLine();
        
        int age;
        while (true) {
        	System.out.print("나이: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } else {
                System.out.println("숫자를 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }
		}
        
        int dangerLevel;
        while (true) {
        	System.out.print("위험 단계: ");
            if (scanner.hasNextInt()) {
            	dangerLevel = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } else {
                System.out.println("숫자를 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }
		}
        
        Chap14Q1Dinosaur newDinosaur = new Chap14Q1Dinosaur(name, species, size, age, dangerLevel);
        system.addDinosaur(newDinosaur);
        System.out.println();
    }
    
    private static void removeDinosaurMenu(Chap14Q2ProjectDinosaurCareSystem system, Scanner scanner) {
        sampleDinosaursList(system);
        System.out.println("제거할 공룡의 이름을 입력하세요:");
        String name = scanner.nextLine();

        // 공룡 이름으로 제거할 공룡 찾기
        boolean found = false;
        for (Chap14Q1Dinosaur dinosaur : system.getDinosaurs()) {
            if (dinosaur.getName().equalsIgnoreCase(name)) {
            	system.getDinosaurs().remove(dinosaur);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("해당 이름의 공룡이 존재하지 않습니다.");
        } else {
            System.out.println(name + "이(가) 제거되었습니다.");
        }
    }
      
    private static void sampleDinosaursList(Chap14Q2ProjectDinosaurCareSystem system) {
        List<Chap14Q1Dinosaur> dinosaurs = system.getDinosaurs();
        System.out.println("공룡 목록:");
        for (int i = 0; i < dinosaurs.size(); i++) {
        	Chap14Q1Dinosaur dinosaur = dinosaurs.get(i);
            System.out.println((i + 1) + ". 이름: " + dinosaur.getName() + ", 종: " + dinosaur.getSpecies() + 
            ", 크기: " + dinosaur.getSize() + ", 나이: " + dinosaur.getAge() + 
            ", 위험 레벨: " + dinosaur.getDangerLevel());
            System.out.println("=============================");
        }
    }
    
    private static void getDinosaursList(Chap14Q2ProjectDinosaurCareSystem system, Scanner scanner) {
        while (true) {
        	System.out.println();
            System.out.println("공룡 목록 보기:");
            System.out.println("1. 공룡 정렬하기 (나이순)");
            System.out.println("2. 공룡 정렬하기 (위험 단계순)");
            System.out.println("3. 공룡 정렬하기 (크기순)");
            System.out.println("4. 이전 메뉴로 돌아가기");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                case 1:
                    displaySortedDinosaurs(system.sortDinosaursByAge());
                    break;
                case 2:
                    displaySortedDinosaurs(system.sortDinosaursByDangerLevel());
                    break;
                case 3:
                    displaySortedDinosaurs(system.sortDinosaursBySize());
                    break;
                case 4:
                    return; // 이전 메뉴로 돌아가기
                default:
                    System.out.println("올바른 선택이 아닙니다. 다시 선택하세요.");
            }
        } else {
            System.out.println("숫자를 입력하세요.");
            scanner.nextLine(); // 입력 버퍼 비우기
            }
        }
    }
    
    private static void displaySortedDinosaurs(List<Chap14Q1Dinosaur> sortedDinosaurs) {
        // 정렬된 공룡 출력
        for (Chap14Q1Dinosaur dinosaur : sortedDinosaurs) {
            System.out.println("이름: " + dinosaur.getName() + ", 나이: " + dinosaur.getAge() + ", 위험 레벨: " + dinosaur.getDangerLevel() + ", 크기: " + dinosaur.getSize());
        }
    }
    
    private static void logActivity(Chap14Q2ProjectDinosaurCareSystem system, Scanner scanner) {
        System.out.println("활동을 기록합니다.");
        System.out.print("활동 이름: ");
        String activityName = scanner.nextLine();
        system.logActivity(activityName);
        System.out.println();
    }
    
    private static void removeActivityMenu(Chap14Q2ProjectDinosaurCareSystem system, Scanner scanner) {
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

    private static void getActivitiesList(Chap14Q2ProjectDinosaurCareSystem system) {
        List<Chap14Q3ProjectActivity> activities = system.getActivities();
        System.out.println("활동 목록:");
        for (int i = 0; i < activities.size(); i++) {
        	Chap14Q3ProjectActivity activity = activities.get(i);
            System.out.println((i + 1) + ". 활동 이름: " + activity.getActivityName() + ", 날짜: " + activity.getDate() + ", 활동한 공룡: " + activity.getDinosaur().getName());
        }
        System.out.println();
    }

}
