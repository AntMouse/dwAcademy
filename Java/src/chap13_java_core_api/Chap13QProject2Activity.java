package chap13_java_core_api;

import java.time.LocalDate;

public class Chap13QProject2Activity {
    private String activityName;
    private LocalDate date;
    private Chap13QProject1Dinosaur dinosaur;

    public Chap13QProject2Activity(String activityName, LocalDate date, Chap13QProject1Dinosaur dinosaur) {
        this.activityName = activityName;
        this.date = date;
        this.dinosaur = dinosaur;
    }

    // 각 속성의 Getter 및 Setter 메서드
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Chap13QProject1Dinosaur getDinosaur() {
        return dinosaur;
    }

    public void setDinosaur(Chap13QProject1Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
