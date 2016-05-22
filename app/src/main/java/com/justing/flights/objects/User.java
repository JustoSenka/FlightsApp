package com.justing.flights.objects;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by JustInG on 5/14/2016.
 */
public class User {

    private long id;
    private String firstName, lastName, password, email;
    private Date birthDate;

    private SortedSet<Flight> myFlights;

    public User(){
        this(null, null, null, null, null);
    }

    public User(String email, String password, String firstName, String lastName, Date birthDate) {
        this(-1, email, password, firstName, lastName, birthDate, new TreeSet<Flight>());
    }

    public User(long id, String email, String password, String firstName, String lastName, Date birthDate){
        this(id, email, password, firstName, lastName, birthDate, new TreeSet<Flight>());
    }

    public User(long id, String email, String password, String firstName, String lastName, Date birthDate, SortedSet<Flight> myFlights) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.myFlights = myFlights;
    }

    public boolean isRegisteredUser(){
        return email != null;
    }

    public SortedSet<Flight> getMyFlights() {
        return myFlights;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
