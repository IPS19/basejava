package com.urise.webapp.util;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final YearMonth NOW = YearMonth.of(3000,1);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");


    public static String format(YearMonth date) {
        if (date == null) return "";
        return date.equals(NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
    }

    public static YearMonth parse(String date) {
        if (HtmlUtil.isEmpty(date) || "Сейчас".equals(date)) return NOW;
        YearMonth yearMonth = YearMonth.parse(date, DATE_FORMATTER);
        return YearMonth.of(yearMonth.getYear(), yearMonth.getMonth());
    }
}

