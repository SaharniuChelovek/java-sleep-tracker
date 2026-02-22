package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime start, LocalDateTime end, SleepRating rating) {

    @Override
    public LocalDateTime start() {
        return start;
    }

    @Override
    public LocalDateTime end() {
        return end;
    }

    @Override
    public SleepRating rating() {
        return rating;
    }
}
