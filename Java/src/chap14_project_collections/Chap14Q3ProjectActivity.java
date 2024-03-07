package chap14_project_collections;

import chap14_collections_and_generics.Chap14Q1Dinosaur;

import java.time.LocalDate;

public class Chap14Q3ProjectActivity {
    private String activityName;
    private LocalDate date;
    private Chap14Q1Dinosaur dinosaur;

    public Chap14Q3ProjectActivity(String activityName, LocalDate date, Chap14Q1Dinosaur dinosaur) {
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

    public Chap14Q1Dinosaur getDinosaur() {
        return dinosaur;
    }

    public void setDinosaur(Chap14Q1Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
