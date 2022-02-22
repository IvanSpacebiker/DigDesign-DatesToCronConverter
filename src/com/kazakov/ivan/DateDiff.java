package com.kazakov.ivan;

import java.util.Calendar;

public class DateDiff {

    int secs;
    int mins;
    int hrs;
    int day;
    int month;
    int year;
    int freq = 1;


    DateDiff(Calendar c1, Calendar c2) {
        this.secs = c2.get(Calendar.SECOND) - c1.get(Calendar.SECOND);
        this.mins = c2.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
        this.hrs = c2.get(Calendar.HOUR) - c1.get(Calendar.HOUR);
        this.day = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        this.month = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        this.year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
    }

    void freqIncrease(){
        this.freq += 1;
    }

    public int getFreq() {
        return freq;
    }

    public void getDiff(){
        System.out.format("\n%d:%d:%d %d-%d-%d", secs, mins, hrs, day, month, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DateDiff myObject = (DateDiff) obj;
        return secs == myObject.secs &&
                mins == myObject.mins &&
                hrs == myObject.hrs &&
                day == myObject.day &&
                month == myObject.month &&
                year == myObject.year;
    }

}
