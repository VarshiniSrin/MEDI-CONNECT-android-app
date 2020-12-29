package com.example.myapplication;

public class Items_DisplayReports {
    private String time;
    private String disease;
    private String symptoms;

    public Items_DisplayReports(String time, String disease, String symptoms) {
        this.time = time;
        this.disease = disease;
        this.symptoms = symptoms;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
