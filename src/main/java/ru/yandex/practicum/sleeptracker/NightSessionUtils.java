package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;

public class NightSessionUtils {

    public boolean isNightSession(SleepingSession session) {
        return !session.start().toLocalDate().equals(session.end().toLocalDate()) ||
                session.start().toLocalTime().isAfter(LocalTime.of(22, 0)) ||
                session.end().toLocalTime().isBefore(LocalTime.of(9, 0));
    }
}
