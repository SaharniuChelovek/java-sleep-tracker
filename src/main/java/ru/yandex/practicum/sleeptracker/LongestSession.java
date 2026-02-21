package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class LongestSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "самая длинная ночная сессия в минутах";
    static final String ERROR_TITLE = "ошибка в LongestSession, не найден элемент";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<SleepingSession> longestSession = sessions.stream()
                .max(Comparator.comparingLong(session ->
                        Duration.between(session.start(), session.end()).toMinutes()));

        if (longestSession.isEmpty()) {
            throw new NoSuchElementException(ERROR_TITLE);
        }
        SleepingSession result = longestSession.get();

        return new SleepAnalysisResult(FUNCTION_TITLE, Duration.between(result.start(), result.end()).toMinutes());
    }
}
