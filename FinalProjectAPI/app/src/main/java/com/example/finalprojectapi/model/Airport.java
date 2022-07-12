package com.example.finalprojectapi.model;

public class Airport {

    private int id;
    private String city;
    private String nameAirport;
    private String phone;

    public Airport(int id, String city,  String nameAirport, String phone) {
        this.id = id;
        this.city = city;
        this.nameAirport = nameAirport;
        this.phone = phone;
    }

    public Airport() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

}
