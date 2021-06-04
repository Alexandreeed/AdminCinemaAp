package com.example.phoneauth;

public class Place {
    private String numberOfZal;
    private String row;
    private String seat;

    public Place(String numberOfZal, String row, String seat) {
        this.numberOfZal = numberOfZal;
        this.row = row;
        this.seat = seat;
    }

    public String getNumberOfZal() {
        return numberOfZal;
    }

    public void setNumberOfZal(String numberOfZal) {
        this.numberOfZal = numberOfZal;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
