package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class InsomniaAnalysisTest {

    InsomniaAnalysis ia = new InsomniaAnalysis();

    @Test
    void acrossMonthTest() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.of(2024, 1, 31, 23, 30), LocalDateTime.of(2024, 2, 1, 6, 30), SleepRating.GOOD), new SleepingSession(LocalDateTime.of(2024, 2, 1, 23, 45), LocalDateTime.of(2024, 2, 2, 6, 15), SleepRating.GOOD));

        SleepAnalysisResult result = ia.apply(sessions);

        Assertions.assertEquals(0L, result.getResult());
    }

    @Test
    void acrossMonthInsomniaTest() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.of(2024, 1, 31, 12, 0), LocalDateTime.of(2024, 1, 31, 13, 0), SleepRating.BAD), new SleepingSession(LocalDateTime.of(2024, 2, 1, 12, 0), LocalDateTime.of(2024, 2, 1, 13, 0), SleepRating.BAD));

        SleepAnalysisResult result = ia.apply(sessions);

        Assertions.assertEquals(1L, result.getResult());
    }

    @Test
    void acrossMidnightTest() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.of(2024, 3, 1, 1, 0), LocalDateTime.of(2024, 3, 1, 7, 0), SleepRating.GOOD), new SleepingSession(LocalDateTime.of(2024, 3, 2, 1, 30), LocalDateTime.of(2024, 3, 2, 6, 30), SleepRating.NORMAL));

        SleepAnalysisResult result = ia.apply(sessions);

        Assertions.assertEquals(0L, result.getResult());
        Assertions.assertEquals(2, sessions.size());
    }

    @Test
    void emptyListTest() {
        SleepAnalysisResult result = ia.apply(List.of());

        Assertions.assertEquals(0L, result.getResult());
    }
}
