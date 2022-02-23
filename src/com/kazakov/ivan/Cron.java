package com.kazakov.ivan;

import java.util.*;

public class Cron {

    private int start, end;
    private List<Integer> secs = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();
    private List<Integer> hrs = new ArrayList<>();
    private List<Integer> days = new ArrayList<>();
    private List<Integer> months = new ArrayList<>();


    public Cron(List<Calendar> dates) {

        for (Calendar date : dates) {
            this.secs.add(date.get(Calendar.SECOND));
            this.mins.add(date.get(Calendar.MINUTE));
            this.hrs.add(date.get(Calendar.HOUR));
            this.days.add(date.get(Calendar.DAY_OF_MONTH));
            this.months.add(date.get(Calendar.MONTH));
        }

        List<Integer> startAndEnd= getLongestList(months);
        this.start = startAndEnd.get(0);
        this.end = startAndEnd.get(1);
    }

    public List<Integer> getLongestList (List < Integer > param) {
        int start = 0, end = 0, len = 1, maxLen = len, maxStart = start, maxEnd = end, cycleStart = start;
        boolean cycle = false;

        for (int i = 1; i < param.size(); i++) {

            if (cycle){ //cycle

                if (param.get(i) != param.get(i - cycleStart + start)){

                    cycle = false;

                    end = i - 1;

                    len = end + 1 - start;

                    if (len > maxLen){
                        maxLen = len;
                        maxStart = start;
                        maxEnd = end;
                    }
                    start = i;
                }

                if (i == param.size() - 1 && cycle){
                    maxEnd = i;
                }

            }
            else { //no cycle

                if (param.get(i - 1) > param.get(i)){   //start from the smallest

                    if (param.get(i) != param.get(start)){
                        end = i - 1;

                        len = end + 1 - start;

                        if (len > maxLen){
                            maxLen = len;
                            maxStart = start;
                            maxEnd = end;
                        }
                        start = i;
                    }
                    else {
                        cycle = true;
                        if (i == param.size() - 1 && cycle){
                            maxEnd = i;
                        }
                        cycleStart = i;
                    }
                }
                else if (i == param.size() - 1){
                    end = i;

                    len = end + 1 - start;

                    if (len > maxLen){
                        maxLen = len;
                        maxStart = start;
                        maxEnd = end;
                    }
                }

            }
        }

        return Arrays.asList(maxStart, maxEnd);
    }

    public int getStart () {
        return start;
    }

    public int getEnd () {
        return end;
    }



}
