package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class ShortestSession implements Function<List<SleepingSession>, SleepAnalysisResult> {


    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<SleepingSession> shortestSession = sessions.stream()
                .min(Comparator.comparingLong(session ->
                        Duration.between(session.start(), session.end()).toMinutes()));

        if (shortestSession.isEmpty()) {
            throw new NoSuchElementException("ошибка в shortestSession, не найден элемент");
        }
        SleepingSession result = shortestSession.get();

        return new SleepAnalysisResult("самая короткая ночная сессия в минутах", Duration.between(result.start(), result.end()).toMinutes());
    }
}
