package com.example.workoutapp.models;


public class MyWorkout {
    private int id;
    private String start;
    private String end;
    private int isFinished;
    private int numberOfExercises;
    private String duration;
    private String place;

    public MyWorkout(int id, String start, String end, int numberOfExercises, int isFinished, String duration, String place) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isFinished = isFinished;
        this.numberOfExercises = numberOfExercises;
        this.duration = duration;
        this.place = place;

    }

    public MyWorkout(String start, String end,int numberOfExercises, int isFinished,  String duration, String place) {
        this.start = start;
        this.end = end;
        this.isFinished = isFinished;
        this.numberOfExercises = numberOfExercises;
        this.duration = duration;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int isFinished() {
        return isFinished;
    }

    public void setFinished(int finished) {
        isFinished = finished;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "MyWorkout{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", isFinished=" + isFinished +
                ", numberOfExercises=" + numberOfExercises +
                ", duration=" + duration +
                '}';
    }
}
