package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InsomniaAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "Количество бессонных ночей";

    NightSessionUtils nsu = new NightSessionUtils();

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(FUNCTION_TITLE, 0L);
        }

        Set<LocalDate> loggedDates = sessions.stream()
                .map(session -> session.start().toLocalDate())
                .collect(Collectors.toSet());

        long totalNights = loggedDates.stream()
                .filter(date -> loggedDates.contains(date.plusDays(1)))
                .count();

        Set<LocalDate> nightsWithSleep = sessions.stream()
                .filter(session -> nsu.isNightSession(session))
                .map(session -> resolveNightDate(session))
                .collect(Collectors.toSet());

        long insomniaCount = totalNights -
                nightsWithSleep.stream()
                        .filter(date -> loggedDates.contains(date)
                                && loggedDates.contains(date.minusDays(1)))
                        .count();

        return new SleepAnalysisResult(FUNCTION_TITLE, insomniaCount);
    }

    private LocalDate resolveNightDate(SleepingSession session) {

        LocalDateTime start = session.start();
        LocalDateTime end = session.end();

        if (end.isBefore(start)) {
            end = end.plusDays(1);
        }

        if (start.toLocalTime().isBefore(LocalTime.of(6, 0))) {
            return start.toLocalDate();
        }

        return start.toLocalDate().plusDays(1);
    }
}
//на 90% уверен что неправильно, но я уже не знаю, как это лучше написать. Простите