package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class NightsCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long nightsCount = sessions.stream()
                .filter(session -> isNightSession(session))
                .count();

        return new SleepAnalysisResult("Количество ночей", nightsCount);
    }

    public boolean isNightSession(SleepingSession session) {
        return !session.start().toLocalDate().equals(session.end().toLocalDate()) ||
                session.start().toLocalTime().isAfter(LocalTime.of(22,0)) ||
                session.end().toLocalTime().isBefore(LocalTime.of(9,0));
    }
}
