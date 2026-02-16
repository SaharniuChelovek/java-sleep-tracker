package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronoTypeViewer implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long larkTypeCount = sessions.stream()
                .filter(session -> session.start().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                        session.end().toLocalTime().isAfter(LocalTime.of(9, 1)))
                .count();
        long owlTypeCount = sessions.stream()
                .filter(session -> session.start().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                        session.end().toLocalTime().isAfter(LocalTime.of(9, 1)))
                .count();
        long pigeonTypeCount = sessions.stream()
                .filter(session -> session.start().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                        session.end().toLocalTime().isAfter(LocalTime.of(9, 1)))
                .count();

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
