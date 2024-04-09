package com.arcsoft.arcfacedemo.thisapp.util;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author: xtxiaolu
 * @date: 2023/7/14
 * 描述:时间工具处理类
 * https://blog.csdn.net/u014696856/article/details/131728523
 */
public class TimeUtil {

    /**
     * 获取当前时间为本月的第几周
     *
     * @return 本月的第几周
     */
    public static int getCurrentWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        return weekOfMonth;
    }

    /**
     * 获取当前时间为本周的第几天
     *
     * @return 本周的第几天（1代表周一，7代表周日）
     */
//    public static int getCurrentDayOfWeek() {
//        LocalDate currentDate = LocalDate.now();
//        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
//        int dayOfWeekValue = dayOfWeek.getValue();
//        return dayOfWeekValue;
//    }

    /**
     * 返回当前日期是星期几
     *
     * @return 例如：星期日、星期一、星期六等等。
     */
    public static String getCurrentDayOfWeekText() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 获取星期几文本
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        String[] dayOfWeekTexts = symbols.getWeekdays();
        if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.SATURDAY) {
            return dayOfWeekTexts[dayOfWeek];
        } else {
            return "";
        }
    }

    /**
     * 判断指定时间是否在时间区间内
     *
     * @param time        待判断的时间
     * @param startTime   时间区间的开始时间
     * @param endTime     时间区间的结束时间
     * @return 如果指定时间在时间区间内，返回 true；否则返回 false
     */
    public static boolean isTimeInRange(Calendar time, Calendar startTime, Calendar endTime) {
        return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
    }

    /**
     * 判断指定时间是否在时间区间内
     *
     * @param time        待判断的时间
     * @param startTime   时间区间的开始时间
     * @param endTime     时间区间的结束时间
     * @return 如果指定时间在时间区间内，返回 true；否则返回 false
     */
//    public static boolean isTimeInRange(LocalDateTime time, LocalDateTime startTime, LocalDateTime endTime) {
//        return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
//    }

    /**
     * 判断指定时间是否在时间区间内
     *
     * @param currentTime 待判断的时间
     * @param startTime   时间区间的开始时间
     * @param endTime     时间区间的结束时间
     * @return 如果指定时间在时间区间内，返回 true；否则返回 false
     */
    public static boolean isInTimeRange(Date currentTime, Date startTime, Date endTime) {
        long currentTimeMillis = currentTime.getTime();
        return currentTimeMillis >= startTime.getTime() && currentTimeMillis <= endTime.getTime();
    }

    /**
     * 求两个日期相差天数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 相差天数
     */
    public static long calculateDaysDifference(Date startDate, Date endDate) {
        long differenceMillis = endDate.getTime() - startDate.getTime();
        long differenceDays = TimeUnit.MILLISECONDS.toDays(differenceMillis);
        return differenceDays;
    }


    /**
     * 返回友好时间跨度
     *
     * @param date 需要格式化的时间
     *
     * @return the fit time span
     *         return 小于1分钟，返回"刚刚"
     *         return 小于1小时但大于0分钟，返回"X分钟前"
     *         return 小于1天但大于0小时，返回"X小时前"
     *         return 昨天，返回"昨天"
     *         return 大于1天，返回"X天前"
     */
    public static String calculateTimeDifference(Date date) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - date.getTime();
        // 计算时间差对应的单位
        long seconds = timeDifference / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 1) {
            // 大于1天，返回"X天前"
            return days + "天前";
        } else if (days == 1) {
            // 昨天，返回"昨天"
            return "昨天";
        } else if (hours > 0) {
            // 小于1天但大于0小时，返回"X小时前"
            return hours + "小时前";
        } else if (minutes > 0) {
            // 小于1小时但大于0分钟，返回"X分钟前"
            return minutes + "分钟前";
        } else {
            // 小于1分钟，返回"刚刚"
            return "刚刚";
        }
    }
}

