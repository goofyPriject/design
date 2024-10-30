package cn.aireco.platform.sdk.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TimeRangeSplitter {
    public static void main(String[] args) {
        // 定义时间范围的开始和结束
        String startStr = "2024-10-09 00:00:00";
        String endStr = "2024-10-12 00:00:00";

        // 使用 DateTimeFormatter 来格式化日期字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将字符串转换为 LocalDateTime 对象
        LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);

        // 计算开始时间和结束时间之间的天数差
        long daysBetween = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate()) + 1;

        // 创建时间段列表
        List<String[]> timeRanges = new ArrayList<>();

        // 循环每一天，创建时间段
        for (long i = 0; i < daysBetween; i++) {
            // 当天的开始时间
            LocalDateTime startOfDay = startDateTime.plusDays(i).withHour(0).withMinute(0).withSecond(0).withNano(0);
            // 下一天的开始时间（即当前天的结束时间）
            LocalDateTime endOfDay = startOfDay.plusDays(1);

            // 格式化日期字符串并添加到列表
//            timeRanges.add(startOfDay.format(formatter) + "到" + endOfDay.minusSeconds(1).format(formatter));
            timeRanges.add(new String[]{startOfDay.format(formatter), endOfDay.minusSeconds(1).format(formatter)});
        }

        // 打印时间段
        for (String[] range : timeRanges) {
            System.out.println(range[0]+"到"+range[1]);
        }
    }
}