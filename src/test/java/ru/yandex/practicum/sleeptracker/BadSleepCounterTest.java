package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class BadSleepCounterTest {

    static List<SleepingSession> sessions1;
    static List<SleepingSession> sessions2;
    static SleepingSession session1;
    static SleepingSession session2;
    static SleepingSession session3;
    static long zero;
    static long one;

    @BeforeAll
    static void sessionsList() {

        zero = 0;
        one = 1;

        session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 10, 23, 45),
                LocalDateTime.of(2025, 10, 11, 9, 15),
                SleepRating.GOOD);
        session2 = new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 0),
                LocalDateTime.of(2025, 10, 12, 9, 0),
                SleepRating.NORMAL);
        session3 = new SleepingSession(LocalDateTime.of(2025, 10, 12, 23, 0),
                LocalDateTime.of(2025, 10, 13, 9, 0),
                SleepRating.BAD);


        sessions1 = List.of(
                session1, session2);
        sessions2 = List.of(session2, session3);
    }

    @Test
    void noBadTest() {
        BadSleepCounter bsc = new BadSleepCounter();
        Assertions.assertEquals(zero, bsc.apply(sessions1).getResult());
    }

    @Test
    void oneBadTest() {
        BadSleepCounter bsc = new BadSleepCounter();
        Assertions.assertEquals(one, bsc.apply(sessions2).getResult());
    }

}
