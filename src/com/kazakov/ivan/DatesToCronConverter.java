package com.kazakov.ivan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public interface DatesToCronConverter {

    String AUTHOR = "\nauthor: kazakov ivan igorevich";
    String NAME = "\nclass: DatesToCronConverter";
    String PACKAGE = "\npackage: com.kazakov.ivan";
    String GITHUB = "\ngithub: https://github.com/IvanSpacebiker/DigDesign-DatesToCronConverter";
    /**
     * Default date format for input dates
     */
    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);



    /**
     * Method to convert list of dates to cron expression
     *
     * @param dates list of dates
     * @return cron expression ("0 * * * * MON")
     */
    default List<Calendar> convert(List<String> dates) throws ParseException {

        List<Calendar> calList = calListCreate(dates);

        return calList; //should return a Cron
    }


    static String getImplementationInfo(){

        return AUTHOR + NAME + PACKAGE + GITHUB;

    }

    default List<Calendar> calListCreate(List<String> dates) throws ParseException {
        List<Calendar> formatDates = new ArrayList<>();

        for (String dateAndTime : dates){

            Calendar formatDate = toCalendar(dateFormat.parse(dateAndTime));  //creating a Calendar
            formatDates.add(formatDate);
        }

        Collections.sort(formatDates, new CalendarComparator());  //sorting

        return formatDates;
    }

    static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}