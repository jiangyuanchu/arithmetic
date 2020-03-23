package com.jyc.graduation.individuation.utils;

import com.jyc.graduation.config.GlobalVariable;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

/**
 * 时间回弹机制
 * @author 姜沅初
 */
@Slf4j
@Component
public class TimeSpringbackMechanism {

    @Autowired
    DateUtils dateUtils;

    public Date getSpringbackReceipt(Date date ,Integer time){
        if(time > GlobalVariable.CRITICAL_VISITOR_VOLUME){
            long dateTime = date.getTime();
            long nowDateTime = System.currentTimeMillis();
            long springbackValue = timeSpringbackMechanismArithmetic(dateTime, nowDateTime);
            dateTime += springbackValue;
            Date date1 = new Date(dateTime);
            System.out.println("改变前日期 ===：" + date + " 改变后日期 ==== ：" + date1);
            if(date1.after(dateUtils.getTimeOutDayAfter(date))){
                date1 = dateUtils.getTimeOutDayAfter(date);
            }
            return date1;
        }
        return null;
    }

    public Long timeSpringbackMechanismArithmetic(Long dateTime ,Long nowDateTime){
        long long1 = nowDateTime - dateTime;
        //物理学弹性势能公式变种
        BigInteger bigInteger = BigInteger.valueOf(long1);
        bigInteger.multiply(bigInteger);
        BigInteger bigresult = bigInteger.divide(BigInteger.valueOf(2*GlobalVariable.TIME_OUT_DAY));
        long result = bigresult.longValue();
        int time_out_limit = GlobalVariable.TIME_OUT_DAY*24*60*60*1000;
        System.out.println("时间改变量 ======= ：" + result);
        System.out.println("时间差 ======= ：" + long1);
        return result;
    }

}
