package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronoTypeViewer implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "классификация";
    static final LocalTime LARK_TIME_START = LocalTime.of(22, 0);
    static final LocalTime LARK_TIME_END = LocalTime.of(7, 0);
    static final LocalTime OWL_TIME_START = LocalTime.of(23, 0);
    static final LocalTime OWL_TIME_END = LocalTime.of(9, 0);

    NightSessionUtils nsn = new NightSessionUtils();

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long larkTypeCount = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .filter(session ->
                        session.start().toLocalTime().isBefore(LARK_TIME_START) &&
                                session.end().toLocalTime().isBefore(LARK_TIME_END))
                .count();

        long owlTypeCount = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .filter(session ->
                        session.start().toLocalTime().isAfter(OWL_TIME_START) &&
                                session.end().toLocalTime().isAfter(OWL_TIME_END))
                .count();

        long totalNightSessions = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .count();

        long pigeonTypeCount = totalNightSessions - larkTypeCount - owlTypeCount;

        ChronoType chronoType = chronoDetector(larkTypeCount, owlTypeCount, pigeonTypeCount);

        return new SleepAnalysisResult(FUNCTION_TITLE, chronoType);
    }

    public ChronoType chronoDetector(long lark, long owl, long pigeon) {
        if (lark > owl && lark > pigeon) {
            return ChronoType.LARK;
        } else if (owl > lark && owl > pigeon) {
            return ChronoType.OWL;
        }
        return ChronoType.PIGEON;

    }


}
