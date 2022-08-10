package com.qunar.mybatis.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;

import java.util.Date;

/**
 * @ClassName DateUtils * @Description TODO
 * @Author tako
 * @Date 15:10 2022/8/10
 * @Version 1.0
 **/
public class DateUtils {

    public static Date dateAdd(Date date, int num) {
        DateTime dateTime = new DateTime(date);
        DateTime plusDays = dateTime.plusDays(num);
        return plusDays.toDate();
    }
}
