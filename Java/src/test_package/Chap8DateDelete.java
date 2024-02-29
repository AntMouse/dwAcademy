package test_package;

public class Chap8DateDelete {
	// 각 공룡 추가 메서드 통합 관리
    public void addDinosaur(int index, String dinoName, int dinoAge, String dinoSpecies, String dinoStatus) {
        if (0 <= index && index < dino.length) {
            if (isRegistered(index, dino)) {
                dino[index] = new Chap8Q1Dinosaur(dinoName, dinoAge, dinoSpecies, dinoStatus);
            } else {
                int findEmptyIndex = findEmptyIndex(dino); // 비어있는 공간 있는지 체크
                if (findEmptyIndex != -1) {
                	System.out.println("해당 인덱스에는 이미 공룡이 등록되어 있습니다.");
                	System.out.println(findEmptyIndex + " 번은 비어있으니 공룡을 등록하려면 " + 
                	findEmptyIndex + " 번에 등록해주세요.");
				} else {
					System.out.println("현재 빈 저장 공간이 없어서 더 이상 공룡을 등록할 수 없습니다.");
				}
            }
        } else {
            indexErrorMessage();
        }
    }
    
	// 해당 직원 스케줄값 얻기
	public String getEmployeeSchedule() {
		StringBuilder scheduleString = new StringBuilder();
	    if (schedules == null || schedules.length == 0) {
	        return "해당 직원은 현재 일정이 비어있습니다.";
	    } else {
	        scheduleString.append("직원 ").append(employeeName).append("의 스케줄:\n");
	        for (int i = 0; i < schedules.length; i++) {
	            if (!schedules[i].isEmpty()) {
	                scheduleString.append(i + 1).append(". ").append(schedules[i]).append("\n");
	            }
	        }
	    }
	    return scheduleString.toString();
	}
	
	// 해당 직원 스케줄 삭제하기
	public void deleteEmployeeSchedule(int index) {
	    if (0 <= index && index < schedules.length) {
	        if (!schedules[index].isEmpty()) {
	            System.out.print(schedules[index] + " 번 스케줄을 삭제하시겠습니까?\n" + " (y/n): ");
	            String choice = scanner.nextLine();
	            if (choice.equalsIgnoreCase("y")) {
	                schedules[index] = null; // 스케줄을 삭제함
	                System.out.println("직원 " + employeeName + "의 " + index + "번째 스케줄이 삭제되었습니다.");
	            } else {
	                System.out.println("삭제를 취소합니다.");
	            }
	        } else {
	            System.out.println("해당 인덱스에는 삭제할 스케줄이 없습니다.");
	        }
	    } else {
	        System.out.println("유효하지 않은 인덱스입니다.");
	    }
	}

	// 해당 직원 스케줄 바꾸기
	public void changeEmployeeSchedule(int index, String newSchedule) {
	    if (0 <= index && index < schedules.length) {
	        if (!schedules[index].isEmpty()) {
	            System.out.print("해당 스케줄에 이미 내용이 있습니다. 변경하시겠습니까? (y/n): ");
	            String choice = scanner.nextLine();
	            if (choice.equalsIgnoreCase("y")) {
	                schedules[index] = newSchedule;
	                System.out.println("직원 " + employeeName + "의 " + index + "번째 스케줄이 변경되었습니다.");
	            } else {
	                System.out.println("변경을 취소합니다.");
	            }
	        } else {
	            schedules[index] = newSchedule;
	            System.out.println("직원 " + employeeName + "의 " + index + "번째 스케줄이 변경되었습니다.");
	        }
	    } else {
	        System.out.println("유효하지 않은 인덱스입니다.");
	    }
	}


}
