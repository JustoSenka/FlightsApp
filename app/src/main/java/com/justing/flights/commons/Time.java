package com.justing.flights.commons;

/**
 * Created by JustInG on 5/13/2016.
 */
public class Time {

    private long time;

    public Time(){}
    public Time(long millis){
        this.time = millis;
    }

    public Time(String str){ this.time = Time.parse(str).getTime(); }
    public Time(int hours, int mins){this(hours, mins, 0);}
    public Time(int hours, int mins, int secs){
        this.time = hours * 3600000 + mins * 60000 + secs * 1000;
    }

    @Override
    public String toString(){
        String str = "";
        if (getMins() < 10){
            str = getHours() + ":0" + getMins();
        }
        else {
            str = getHours() + ":" + getMins();
        }

        return str;
    }

    public static Time parse(String str){
        Time ret = null;
        String[] parts = str.split(":");

        if (parts.length == 2){
            ret = new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }

        return ret;
    }

    public int getHours(){
        return (int) (time / 3600000);
    }

    public int getMins(){
        return (int) ((time % 3600000) / 60000);
    }

    public int getSecs(){
        return (int) (((time % 3600000) % 60000) / 1000);
    }

    public long getTime(){
        return time;
    }

}
