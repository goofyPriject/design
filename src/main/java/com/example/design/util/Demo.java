package com.example.design.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class Demo {

    public static Date getMaxDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime maxDateTime = localDateTime.with(LocalTime.MIN);
        return Date.from(maxDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getMaxDay(new Date()));
    }
}
