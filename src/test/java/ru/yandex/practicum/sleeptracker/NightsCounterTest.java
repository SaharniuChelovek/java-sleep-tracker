package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class NightsCounterTest {

    static List<SleepingSession> sessions;
    static SleepingSession session1;
    static SleepingSession session2;

    @BeforeAll
    static void sessionsList() {

        session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 11, 10, 45),
                LocalDateTime.of(2025, 10, 11, 16, 15),
                SleepRating.GOOD);
        session2 = new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 0),
                LocalDateTime.of(2025, 10, 12, 9, 0),
                SleepRating.GOOD);

        sessions = List.of(
                session1, session2);
    }


    @Test
    void oneNightTest() {
        NightsCounter nc = new NightsCounter();
        long nightNumber = 1;

        Assertions.assertEquals(nightNumber, nc.apply(sessions).getResult());
        Assertions.assertEquals(2, sessions.size());
    }
}
