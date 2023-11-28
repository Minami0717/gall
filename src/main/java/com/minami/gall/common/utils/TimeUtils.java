package com.minami.gall.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    public static long timeDiffCalc() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 23, 59, 59);
        return ChronoUnit.SECONDS.between(start, end);
    }
}
