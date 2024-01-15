package cn.aireco.platform.gy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LwService {

    public static void main(String[] args) {
        Date currentDate = getBeforeOneDayTime(true);
        Date previousDay = getPreviousDay(currentDate);
        System.out.println(previousDay);

        List<String> list = Arrays.asList("A", "B", "C", "D");
        Set<String> listS = new HashSet<>(list);
        String joinedString = String.join(",", listS);
        System.out.println(joinedString);

        Map<String, String> map = new HashMap<>();
        LocalDate now = LocalDate.now();
        LocalDateTime localDateTime = now.atStartOfDay();
        System.out.println(localDateTime);
        LocalTime startTime = LocalTime.of(0, 0, 0);
        System.out.println(startTime);

        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put("name", "0001");


    }

    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    private static Date getBeforeOneDayTime(Boolean boo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1); // 获取前一天的日期

        // 设置时间为当天的开始时间（00:00:00）
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date startDate = calendar.getTime();

        // 设置时间为当天的结束时间（23:59:59）
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date endDate = calendar.getTime();
        return boo ? startDate : endDate;
    }



}
