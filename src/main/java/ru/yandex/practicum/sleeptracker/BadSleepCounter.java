package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;


public class BadSleepCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "Количество ночей с плохим сном";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long badDreamCount = sessions.stream()
                .filter(session -> session.rating() == SleepRating.BAD)
                .count();

        return new SleepAnalysisResult(FUNCTION_TITLE, badDreamCount);
    }
}
