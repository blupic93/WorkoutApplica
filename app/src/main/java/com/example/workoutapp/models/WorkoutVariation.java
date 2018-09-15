package com.example.workoutapp.models;

public class WorkoutVariation {

    //fieldovi, isto kao i tablice u bazi
    private int id;
    private int workoutId;
    private String name;
    private String subType;

    //konstruktor, treba ti za select na bazi
    public WorkoutVariation(int id, int workoutId, String name, String subType) {
        this.id = id;
        this.workoutId = workoutId;
        this.name = name;
        this.subType = subType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "WorkoutVariation{" +
                "id=" + id +
                ", workoutId=" + workoutId +
                ", name='" + name + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }
}
