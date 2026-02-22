package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class ShortestSession implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "самая короткая ночная сессия в минутах";
    static final String ERROR_TITLE = "ошибка в ShortestSession, не найден элемент";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        Optional<SleepingSession> shortestSession = sessions.stream()
                .min(Comparator.comparingLong(session ->
                        Duration.between(session.start(), session.end()).toMinutes()));

        if (shortestSession.isEmpty()) {
            throw new NoSuchElementException(ERROR_TITLE);
        }
        SleepingSession result = shortestSession.get();

        return new SleepAnalysisResult(FUNCTION_TITLE, Duration.between(result.start(), result.end()).toMinutes());
    }
}
