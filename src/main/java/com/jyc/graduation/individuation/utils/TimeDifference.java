package com.jyc.graduation.individuation.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TimeDifference {

    public static Double getDayTimeDifferenceDistanceNow(Timestamp timestamp){

        System.out.println("timestamp==========: "+timestamp);

        Date date = new Date();
        long from = timestamp.getTime();
        long to = date.getTime();
        double days = ((to - from) * 1.0 / (1000 * 60 * 60 * 24 * 1.0));
        System.out.println("差值天数========== : "+days);
        return days;

    }

}
