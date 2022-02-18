package com.kazakov.ivan;

import java.util.Comparator;

public class DateComparator implements Comparator<DateToCalendar> {

    @Override
    public int compare(DateToCalendar o1, DateToCalendar o2) {
        return o1.fulltime < o2.fulltime ? -1 : o1.fulltime == o2.fulltime ? 0 : 1;
    }
}
