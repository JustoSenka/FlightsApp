package com.justing.flights.objects;

import com.android.internal.util.Predicate;

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
    private User currentUser;

    public SortedSet<Flight> getAvailableFlights() {
        return availableFlights;
    }

    public Flight getFlightById(long id){
        for (Flight f : availableFlights) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    public SortedSet<Flight> getFilteredAvailableFlights(Predicate<Flight> predicate) {
        return getFilteredFlights(availableFlights, predicate);
    }

    public SortedSet<Flight> getFilteredFlights(SortedSet<Flight> flights, Predicate<Flight> predicate) {
        SortedSet<Flight> filtered = new TreeSet<Flight>();

        for (Flight f : flights){
            if (predicate.apply(f)){
                filtered.add(f);
            }
        }
        return filtered;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
