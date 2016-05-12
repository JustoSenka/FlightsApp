package com.justing.flights.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by JustInG on 5/10/2016.
 */
public class Client {

    private String email, firstName, lastName, password;
    private Date birthDate;
    private SortedSet<Flight> myFlights = new TreeSet<Flight>();

    public Client(String email, String firstName, String lastName, String password, Date birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthDate = birthDate;
    }

    public Client() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public SortedSet<Flight> getMyFlights() {
        return myFlights;
    }
}
