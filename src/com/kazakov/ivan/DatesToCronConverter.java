package com.kazakov.ivan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public interface DatesToCronConverter {

    String AUTHOR = "\nauthor: kazakov ivan";
    String NAME = "\nclass: DatesToCronConverter";
    String PACKAGE = "\npackage: com.kazakov.ivan";
    String GITHUB = "\ngithub: https://github.com/IvanSpacebiker/DigDesign-DatesToCronConverter";


    /**
     * Default date format for input dates
     */
    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * Method to convert list of dates to cron expression
     *
     * @param dates list of dates
     * @return cron expression ("0 * * * * MON")
     */
    default List<DateToCalendar> convert(List<String> dates){

        List<DateToCalendar> formatedDates = new ArrayList<>();

        for (String dateAndTime : dates){
            DateToCalendar formatedDate = new DateToCalendar(dateAndTime); //creating an Object with date parts
            formatedDates.add(formatedDate);
        }

        Collections.sort(formatedDates, new DateComparator());

        return formatedDates; //returning a List of unsorted Objects
    }


    static String getImplementationInfo(){

        return AUTHOR + NAME + PACKAGE + GITHUB;

    }

}