package com.kazakov.ivan;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Arrays;
import java.util.Collections;

public class Cron {

    private int start, end;
    private List<Integer> secs = new ArrayList<>();
    private List<Integer> mins = new ArrayList<>();
    private List<Integer> hrs = new ArrayList<>();
    private List<Integer> days = new ArrayList<>();
    private List<Integer> months = new ArrayList<>();
    private String cron;

    public Cron(List<Calendar> dates) {

        for (Calendar date : dates) {
            this.secs.add(date.get(Calendar.SECOND));
            this.mins.add(date.get(Calendar.MINUTE));
            this.hrs.add(date.get(Calendar.HOUR_OF_DAY));
            this.days.add(date.get(Calendar.DAY_OF_MONTH));
            this.months.add(date.get(Calendar.MONTH) + 1);
        }


        List<Integer> secsSpecs = getLongestList(secs);
        List<Integer> minsSpecs = getLongestList(mins);
        List<Integer> hoursSpecs = getLongestList(hrs);
        List<Integer> daysSpecs = getLongestList(days);
        List<Integer> monthsSpecs = getLongestList(months);
        List<Integer> start = Arrays.asList(secsSpecs.get(0), minsSpecs.get(0), hoursSpecs.get(0), daysSpecs.get(0), monthsSpecs.get(0));
        List<Integer> end = Arrays.asList(secsSpecs.get(1), minsSpecs.get(1), hoursSpecs.get(1), daysSpecs.get(1), monthsSpecs.get(1));


        //throw exception if (start > end || end - start + 1 < dates.size() / 2)
        this.start = Collections.max(start);
        this.end = Collections.min(end);

        StringBuilder cron = new StringBuilder();
        cron
                .append(createCronParam(secs, secsSpecs)).append(" ")
                .append(createCronParam(mins, minsSpecs)).append(" ")
                .append(createCronParam(hrs, hoursSpecs)).append(" ")
                .append(createCronParam(days, daysSpecs)).append(" ")
                .append(createCronParam(months, monthsSpecs));

        this.cron = cron.toString();


    }

    public List<Integer> getLongestList (List<Integer> param) {
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

        return Arrays.asList(maxStart, maxEnd, cycleStart);
    }

    public String createCronParam(List<Integer> param, List<Integer> paramSpecs){
        int last = paramSpecs.get(2) != 0 ? paramSpecs.get(2) - 1 : end;
        String lastAppended = "";

        StringBuilder cronParam = new StringBuilder();
        cronParam.append(param.get(start));


        for (int i = start + 1; i <= last; i++){
            String appended = "/" + param.get(i);
            if (!cronParam.toString().equals(param.get(i).toString()) && !appended.equals(lastAppended)){
                cronParam.append(appended);
            }
            lastAppended = appended;
        }

        return cronParam.toString();
    }

    public String getCron() {
        return cron;
    }
}
