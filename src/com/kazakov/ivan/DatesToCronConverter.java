package com.kazakov.ivan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

        List<Calendar> formatedDates = new ArrayList<>();

        for (String dateAndTime : dates){

            Calendar formatedDate = toCalendar(dateFormat.parse(dateAndTime));  //creating an Object with date parts
            formatedDates.add(formatedDate);

        }

        Collections.sort(formatedDates, new CalendarComparator());

        int correctDateCounter = 1;

        for (int i = 0; i < formatedDates.size() - 2; i++){
            DateDiff dateDiff1 = new DateDiff(formatedDates.get(i), formatedDates.get(i+1));
            DateDiff dateDiff2 = new DateDiff(formatedDates.get(i+1), formatedDates.get(i+2));
            if (dateDiff1.equals(dateDiff2)){correctDateCounter += 1;}
        }



        return formatedDates; //returning a List of sorted Calendars
    }


    static String getImplementationInfo(){

        return AUTHOR + NAME + PACKAGE + GITHUB;

    }

    static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}