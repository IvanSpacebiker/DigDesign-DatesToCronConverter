package com.kazakov.ivan;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateToCalendar {

    protected int year;
    protected int month;
    protected int day;
    protected int hours;
    protected int minutes;
    protected int seconds;
    protected long fulltime;

    DateToCalendar(String dateAndTime){

        String[] dateAndTimeSplitted = dateAndTime.split("T");
        String[] date = dateAndTimeSplitted[0].split("-");
        String[] time = dateAndTimeSplitted[1].split(":");

        year = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        day = Integer.parseInt(date[2]);

        hours = Integer.parseInt(time[0]);
        minutes = Integer.parseInt(time[1]);
        seconds = Integer.parseInt(time[2]);

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);

        fulltime = cal.getTimeInMillis();

    }

    public String getDateAndTime(){
        return String.format("%d:%d:%d %d-%d-%d", this.seconds, this.minutes, this.hours, this.day, this.month, this.year);
    }



}
