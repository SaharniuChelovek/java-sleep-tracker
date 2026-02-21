package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

import java.util.function.Function;

public class AverageSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "средняя продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double averageMinutes = sessions.stream()
                .mapToLong(session -> Duration.between(session.start(), session.end()).toMinutes())
                .average()
                .orElse(0.0);


        return new SleepAnalysisResult(FUNCTION_TITLE, averageMinutes);
    }
}
