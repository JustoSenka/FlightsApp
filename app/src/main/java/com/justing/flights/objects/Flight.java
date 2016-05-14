package com.justing.flights.objects;
import com.justing.flights.commons.Time;

import java.util.Date;

/**
 * Created by JustInG on 5/10/2016.
 */
public class Flight implements Comparable<Flight> {

    private static long ID = 0;

    private long id;
    private Date departureTime, arrivalTime;
    private Time duration;
    private String cityFrom, cityTo;
    private Company company;
    private int ticketPrice;

    public Flight(Date departureTime, Date arrivalTime, String cityFrom, String cityTo, Company company){
        this(departureTime, arrivalTime, cityFrom, cityTo, company, 0);
    }
    public Flight(Date departureTime, Date arrivalTime, String cityFrom, String cityTo, Company company, int ticketPricece) {

        this.id = ID++;
        this.ticketPrice = ticketPricece;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.company = company;

        if (arrivalTime != null && departureTime != null) {
            this.duration = new Time(arrivalTime.getTime() - departureTime.getTime());
        }
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public Company getCompany() {
        return company;
    }

    public Time getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public int compareTo(Flight another) {
        if (this.equals(another)) return 0;
        return (int) (this.departureTime.getTime() - another.getDepartureTime().getTime() + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (ticketPrice != flight.ticketPrice) return false;
        if (!departureTime.equals(flight.departureTime)) return false;
        if (!arrivalTime.equals(flight.arrivalTime)) return false;
        if (!cityFrom.equals(flight.cityFrom)) return false;
        if (!cityTo.equals(flight.cityTo)) return false;
        return company == flight.company;

    }

    @Override
    public int hashCode() {
        int result = departureTime.hashCode();
        result = 31 * result + arrivalTime.hashCode();
        result = 31 * result + cityFrom.hashCode();
        result = 31 * result + cityTo.hashCode();
        result = 31 * result + company.hashCode();
        result = 31 * result + ticketPrice;
        return result;
    }
}
