package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class InsomniaAnalysisTest {

    static List<SleepingSession> sessions;
    static List<SleepingSession> sessions2;
    static List<SleepingSession> sessions3;
    static List<SleepingSession> sessions4;
    static SleepingSession session1;
    static SleepingSession session2;
    static SleepingSession session3;
    static SleepingSession session4;
    static SleepingSession session5;
    static SleepingSession session6;
    static long zero;
    static long one;
    static long two;
    InsomniaAnalysis ia = new InsomniaAnalysis();

    @BeforeAll
    static void sessionsList() {
        zero = 0;
        one = 1;
        two = 2;

        session1 = new SleepingSession(LocalDateTime.of(2025, 10, 10, 5, 45), LocalDateTime.of(2025, 10, 11, 10, 15), SleepRating.GOOD);
        session2 = new SleepingSession(LocalDateTime.of(2025, 10, 11, 0, 0), LocalDateTime.of(2025, 10, 12, 6, 0), SleepRating.GOOD);
        session3 = new SleepingSession(LocalDateTime.of(2025, 10, 13, 7, 0), LocalDateTime.of(2025, 10, 13, 14, 0), SleepRating.BAD);
        session4 = new SleepingSession(LocalDateTime.of(2025, 10, 14, 6, 0), LocalDateTime.of(2025, 10, 15, 13, 0), SleepRating.BAD);
        session5 = new SleepingSession(LocalDateTime.of(2025, 10, 15, 22, 0), LocalDateTime.of(2025, 10, 16, 5, 0), SleepRating.BAD);
        session6 = new SleepingSession(LocalDateTime.of(2025, 10, 16, 7, 0), LocalDateTime.of(2025, 10, 16, 18, 0), SleepRating.BAD);

        sessions = List.of(session1, session2);
        sessions2 = List.of(session2, session3);
        sessions3 = List.of(session6, session3);
        sessions4 = List.of(session6, session4, session5);
    }

    @Test
    void noInsomniaNightsTest() {
        Assertions.assertEquals(zero, ia.apply(sessions).getResult());
    }

    @Test
    void oneInsomniaNightTest() {
        Assertions.assertEquals(one, ia.apply(sessions2).getResult());
    }

    @Test
    void twoInsomniaNightTest() {
        Assertions.assertEquals(two, ia.apply(sessions3).getResult());
    }

    @Test
    void oneInsomniaOneUsualNightsTest() {
        long three = 3;
        Assertions.assertEquals(one, ia.apply(sessions4).getResult());
        Assertions.assertEquals(three, sessions4.size());
    }

}
