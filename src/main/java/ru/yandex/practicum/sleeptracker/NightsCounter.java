package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class NightsCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "Количество ночей";

    NightSessionUtils nsn = new NightSessionUtils();

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long nightsCount = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .count();

        return new SleepAnalysisResult(FUNCTION_TITLE, nightsCount);
    }
}
