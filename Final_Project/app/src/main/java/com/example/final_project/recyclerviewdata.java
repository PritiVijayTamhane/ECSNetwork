package com.example.final_project;

public class recyclerviewdata {

private String name;
    private String date;
    private String slot;
    private String vehicle;
    private String charger;

    public recyclerviewdata( String name,String date, String slot, String vehicle, String charger) {
        this.name=name;
        this.date = date;
        this.slot = slot;
        this.vehicle = vehicle;
        this.charger = charger;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }


}
