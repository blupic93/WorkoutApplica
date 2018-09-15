package com.example.workoutapp.models;

public class WorkoutConnectionHelpModel {
    int id;
    int myWorkoutsId;
    int mainWorkoutsId;
    int workoutVariationsId;

    public WorkoutConnectionHelpModel(int id, int myWorkoutsId, int mainWorkoutsId, int workoutVariationsId) {
        this.id = id;
        this.myWorkoutsId = myWorkoutsId;
        this.mainWorkoutsId = mainWorkoutsId;
        this.workoutVariationsId = workoutVariationsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyWorkoutsId() {
        return myWorkoutsId;
    }

    public void setMyWorkoutsId(int myWorkoutsId) {
        this.myWorkoutsId = myWorkoutsId;
    }

    public int getMainWorkoutsId() {
        return mainWorkoutsId;
    }

    public void setMainWorkoutsId(int mainWorkoutsId) {
        this.mainWorkoutsId = mainWorkoutsId;
    }

    public int getWorkoutVariationsId() {
        return workoutVariationsId;
    }

    public void setWorkoutVariationsId(int workoutVariationsId) {
        this.workoutVariationsId = workoutVariationsId;
    }
}
