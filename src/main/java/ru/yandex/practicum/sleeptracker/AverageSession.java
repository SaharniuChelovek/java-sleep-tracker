package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class AverageSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double averageMinutes = sessions.stream()
                .mapToLong(session -> Duration.between(session.start(), session.end()).toMinutes())
                .average()
                .orElse(0.0);
        if (averageMinutes == 0.0) {
            throw new NoSuchElementException("проблема списка в AverageSession");
        }

        return new SleepAnalysisResult("средняя продолжительность сна в минутах", averageMinutes);
    }
}
