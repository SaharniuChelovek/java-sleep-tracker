package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AverageSessionTest {

    static List<SleepingSession> sessions;
    static SleepingSession session1;
    static SleepingSession session2;

    @BeforeAll
    static void sessionsList() {

        session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 10, 23, 45),
                LocalDateTime.of(2025, 10, 11, 9, 15),
                SleepRating.GOOD);
        session2 = new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 0),
                LocalDateTime.of(2025, 10, 12, 9, 0),
                SleepRating.GOOD);

        sessions = List.of(
                session1, session2);
    }

    @Test
    void averageTest() {
        AverageSession averageSession = new AverageSession();
        Double average1 = (double) ((Duration.between(session1.start(), session1.end()).toMinutes() +
                Duration.between(session2.start(), session2.end()).toMinutes()) / sessions.size());

        Double average2 = (Double) averageSession.apply(sessions).getResult();

        Assertions.assertEquals(average1, average2);
        Assertions.assertNotEquals(0.0, average2);
    }
}
