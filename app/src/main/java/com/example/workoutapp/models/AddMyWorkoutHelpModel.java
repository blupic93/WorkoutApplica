package com.example.workoutapp.models;

public class AddMyWorkoutHelpModel {

    private String Name;
    private String Type;
    private String SubType;

    public AddMyWorkoutHelpModel(String name, String type, String subType) {
        Name = name;
        Type = type;
        SubType = subType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    @Override
    public String toString() {
        return "AddMyWorkoutHelpModel{" +
                "Name='" + Name + '\'' +
                ", Type='" + Type + '\'' +
                ", SubType='" + SubType + '\'' +
                '}';
    }
}
