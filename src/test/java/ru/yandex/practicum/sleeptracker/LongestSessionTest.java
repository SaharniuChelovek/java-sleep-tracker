package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class LongestSessionTest {

    static List<SleepingSession> sessions;
    static SleepingSession correctSession;

    @BeforeAll
    static void sessionsList() {

        correctSession = new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 0),
                LocalDateTime.of(2025, 10, 12, 9, 0),
                SleepRating.GOOD);
        sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 10, 23, 45),
                        LocalDateTime.of(2025, 10, 11, 9, 15),
                        SleepRating.GOOD),
                correctSession);
    }

    @Test
    void findshortSession() {
        LongestSession longestSession = new LongestSession();
        SleepAnalysisResult result = longestSession.apply(sessions);
        Assertions.assertTrue(result.getResult() instanceof Long);
        Assertions.assertEquals(Duration.between(correctSession.start(), correctSession.end()).toMinutes(), result.getResult());
    }
}
