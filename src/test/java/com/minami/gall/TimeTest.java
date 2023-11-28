package com.minami.gall;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeTest {
    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 23, 59, 59);
        System.out.println("종료까지 남은 시간(초) : " + ChronoUnit.SECONDS.between(startTime, endTime));
        System.out.println(ChronoUnit.SECONDS.between(startTime, LocalDateTime.of(2023, 11, 28, 23, 59, 59)));
    }
}
