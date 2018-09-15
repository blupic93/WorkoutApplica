package com.example.workoutapp.models;


public class MainWorkout {

    //fieldovi, isto kao i tablice u bazi
    private int id;
    private String name;
    private int numberOfVariations;
    private String type;

    //konstruktor, treba ti za select na bazi
    public MainWorkout(int id, String name, int numberOfVariations, String type) {
        this.id = id;
        this.name = name;
        this.numberOfVariations = numberOfVariations;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfVariations() {
        return numberOfVariations;
    }

    public void setNumberOfVariations(int numberOfVariations) {
        this.numberOfVariations = numberOfVariations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MainWorkout{" +
                "id=" + id +
                ", name=" + name +
                ", numberOfVariations=" + numberOfVariations +
                ", type=" + type +
                '}';
    }
}
