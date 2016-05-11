package com.justing.flights.objects;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JustInG on 5/10/2016.
 */
public class Flight {

    private Date departureTime, arrivalTime, duration;
    private String cityFrom, cityTo;
    private Company company;
    private int ticketPrice;

    public Flight(Date departureTime, Date arrivalTime, String cityFrom, String cityTo, Company company){
        this(departureTime, arrivalTime, cityFrom, cityTo, company, 0);
    }
    public Flight(Date departureTime, Date arrivalTime, String cityFrom, String cityTo, Company company, int ticketPricece) {

        this.ticketPrice = ticketPricece;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.company = company;

        if (arrivalTime != null && departureTime != null) {
            this.duration = new Date(arrivalTime.getTime() - departureTime.getTime());
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

    public Date getDuration() {
        return duration;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
