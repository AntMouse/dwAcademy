package com.example.springsecurityjwt.dto;

public class DinosaurDto {
    private Long id;
    private String dinoName;
    private int dinoAge;
    private int dinoWeight;
    private String dinoDiet;
    private String dinoSpecies;

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
