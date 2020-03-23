package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.config.GlobalVariable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {

    public boolean isSameDay(Date day1, Date day2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    public Date getTimeOutDayAfter(Date date){
        long aWeek = (long) GlobalVariable.TIME_OUT_DAY *24*60*60*1000;
        Date date1 = new Date(date.getTime() - aWeek);
        return date1;
    }
;
    public Timestamp dateToTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    public Date timestampToDate(Timestamp timestamp){
        return new Date(timestamp.getTime());

    }

}
