package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class InsomniaAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        long insomniaCount = sessions.stream()
                .filter(session -> !isNightSleep(session))
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", insomniaCount);
    }

    private boolean isNightSleep(SleepingSession session) {

        LocalDateTime start = session.start();
        LocalDateTime end = session.end();

        boolean crossesMidnight = !start.toLocalDate().equals(end.toLocalDate());
        boolean startedAfterMidnight = start.toLocalTime().isBefore(LocalTime.of(6, 0));

        return crossesMidnight || startedAfterMidnight;
    }
}
