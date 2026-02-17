package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronoTypeViewer implements Function<List<SleepingSession>, SleepAnalysisResult> {

    NightSessionUtils nsn = new NightSessionUtils();

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long larkTypeCount = sessions.stream()
                .filter(session -> nsn.isNightSession(session)) // если у тебя есть такой метод
                .filter(session ->
                        session.start().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                                session.end().toLocalTime().isBefore(LocalTime.of(7, 0)))
                .count();

        long owlTypeCount = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .filter(session ->
                        session.start().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                                session.end().toLocalTime().isAfter(LocalTime.of(9, 0)))
                .count();

        long totalNightSessions = sessions.stream()
                .filter(session -> nsn.isNightSession(session))
                .count();

        long pigeonTypeCount = totalNightSessions - larkTypeCount - owlTypeCount;

        ChronoType chronoType = chronoDetector(larkTypeCount, owlTypeCount, pigeonTypeCount);

        return new SleepAnalysisResult("классификация", chronoType);
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
