package com.justing.flights.objects;

/**
 * Created by JustInG on 5/11/2016.
 */
public enum Company {

    RyanAir("RyanAir"),
    WizzAir("Wizz Air"),
    Norwegian("Norwegian"),
    AirBaltic("AirBaltic");

    public String name;

    private Company(String name){
        this.name = name;
    }
}
