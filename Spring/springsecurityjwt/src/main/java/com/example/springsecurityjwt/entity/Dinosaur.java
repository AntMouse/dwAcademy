package com.example.springsecurityjwt.entity;

import jakarta.persistence.*;

@Entity
public class Dinosaur {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", nullable=false, length=512) // id의 명칭을 공룡 이름 으로 바꿧다.
    private Long id;

	private String dinoName; // 공룡 이름
	private int dinoAge; // 공룡 나이
	private int dinoWeight; // 공룡 몸무게
	private String dinoDiet; // 공룡 식성
	private String dinoSpecies;

    public Dinosaur() {

    }

    public Dinosaur(String dinoName, String dinoSpecies ,int dinoAge, int dinoWeight, String dinoDiet) {
        super();
        this.dinoName = dinoName;
        this.dinoAge = dinoAge;
        this.dinoWeight = dinoWeight;
        this.dinoDiet = dinoDiet;
        this.dinoSpecies = dinoSpecies;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

	public String getDinoName() {
		return dinoName;
	}

	public int getDinoAge() {
		return dinoAge;
	}

	public int getDinoWeight() {
		return dinoWeight;
	}

	public String getDinoDiet() {
		return dinoDiet;
	}

	public String getDinoSpecies() {
		return dinoSpecies;
	}

	public void setDinoName(String dinoName) {
		this.dinoName = dinoName;
	}

	public void setDinoAge(int dinoAge) {
		this.dinoAge = dinoAge;
	}

	public void setDinoWeight(int dinoWeight) {
		this.dinoWeight = dinoWeight;
	}

	public void setDinoDiet(String dinoDiet) {
		this.dinoDiet = dinoDiet;
	}

	public void setDinoSpecies(String dinoSpecies) {
		this.dinoSpecies = dinoSpecies;
	}


    
    









}
