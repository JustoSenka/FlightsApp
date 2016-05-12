package com.justing.flights.objects;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Justas on 05/11/2016.
 */
public class AppData {

    private static AppData instance = new AppData();
    public static AppData getInstance() {
        return instance;
    }
    private AppData() {}

    private final SortedSet<Flight> availableFlights = new TreeSet<Flight>();

    public SortedSet<Flight> getAvailableFlights() {
        return availableFlights;
    }
    public String[] getKnownCities(){
        String[] arr = new String[0];
        arr = getKnownCitiesSet().toArray(arr);
        return arr;
    }

    public SortedSet<String> getKnownCitiesSet(){
        SortedSet<String> citySet = new TreeSet<String>();

        for (Flight f : availableFlights){
            citySet.add(f.getCityFrom());
            citySet.add(f.getCityTo());
        }
        return citySet;
    }
}
