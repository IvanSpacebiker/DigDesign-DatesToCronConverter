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
    default List<DateDiff> convert(List<String> dates) throws ParseException {

        List<Calendar> formatDates = new ArrayList<>();

        for (String dateAndTime : dates){

            Calendar formatDate = toCalendar(dateFormat.parse(dateAndTime));  //creating an Object with date parts
            formatDates.add(formatDate);

        }

        Collections.sort(formatDates, new CalendarComparator());  //sorting

        //dateDiffs frequency block

        List<DateDiff> dateDiffs = diffListCreate(formatDates);
        int maxFreqNum = maxFreqDef(dateDiffs);

        //dateDiffs frequency block


        return dateDiffs; //should return a Cron
    }


    static String getImplementationInfo(){

        return AUTHOR + NAME + PACKAGE + GITHUB;

    }

    static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    default List<DateDiff> diffListCreate(List<Calendar> dates){

        List<DateDiff> dateDiffs = new ArrayList<>();

        for (int i = 0; i < dates.size() - 1; i++){
            DateDiff dateDiff = new DateDiff(dates.get(i), dates.get(i+1));
            dateDiffs.add(dateDiff);
        }

        for (int i = 0; i < dateDiffs.size(); i++){

            DateDiff currentDiff = dateDiffs.get(i);

            for (int j = i + 1; j < dateDiffs.size(); j++) {

                DateDiff compareDiff = dateDiffs.get(j);

                if (currentDiff.equals(compareDiff)){
                    currentDiff.freqIncrease();
                }

            }

        }

        return dateDiffs;
    }

    default int maxFreqDef(List<DateDiff> dates){

        int maxFreq = -1;

        for (int i = 0; i < dates.size() - 1; i++){
            int f = dates.get(i).freq;
            maxFreq = f >= maxFreq && f >= dates.size() / 2 ? i : maxFreq;
        }
        return maxFreq;
    }



}