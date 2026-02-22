package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NightSessionUtils {

    private static final LocalTime NIGHT_START = LocalTime.MIDNIGHT;
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    public boolean isNightSession(SleepingSession session) {

        LocalDateTime start = session.start();
        LocalDateTime end = session.end();


        if (end.isBefore(start)) {
            end = end.plusDays(1);
        }

        return intersectsNight(start, end, start.toLocalDate()) ||
                intersectsNight(start, end, end.toLocalDate());
    }

    private boolean intersectsNight(LocalDateTime start,
                                    LocalDateTime end,
                                    LocalDate date) {

        LocalDateTime nightStart = date.atTime(NIGHT_START);
        LocalDateTime nightEnd = date.atTime(NIGHT_END);

        return start.isBefore(nightEnd) && end.isAfter(nightStart);
    }
}
