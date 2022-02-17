package com.kazakov.ivan;

import java.util.List;


public interface DatesToCronConverter {



    static String AUTHOR = "kazakov ivan";
    static String NAME = "DatesToCronConverter";
    static String PACKAGE = "com.kazakov.ivan";

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


    default String convert(List<String> dates) throws DatesToCronConvertException{
        return null;
    };





    static String getImplementationInfo(){

        String impInfo = AUTHOR + ", " + NAME + ", " + PACKAGE;

        return impInfo;

    };

}















