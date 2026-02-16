package ru.yandex.practicum.sleeptracker;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class LongestSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<SleepingSession> longestSession = sessions.stream()
                .max(Comparator.comparingLong(session ->
                        Duration.between(session.start(), session.end()).toMinutes()));

        if (longestSession.isEmpty()) {
            throw new NoSuchElementException("ошибка в LongestSession, не найден элемент");
        }
        SleepingSession result = longestSession.get();

        return new SleepAnalysisResult("самая длинная ночная сессия в минутах", Duration.between(result.start(), result.end()).toMinutes());
    }
}
