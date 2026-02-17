package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ChronoTypeViewerTest {

    static List<SleepingSession> sessions1;
    static List<SleepingSession> sessions2;
    static SleepingSession session1;
    static SleepingSession session2;
    static SleepingSession session3;

    @BeforeAll
    static void sessionsList() {

        session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 10, 23, 45),
                LocalDateTime.of(2025, 10, 11, 9, 15),
                SleepRating.GOOD);
        session2 = new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 10),
                LocalDateTime.of(2025, 10, 12, 9, 10),
                SleepRating.NORMAL);
        session3 = new SleepingSession(LocalDateTime.of(2025, 10, 12, 22, 0),
                LocalDateTime.of(2025, 10, 13, 6, 0),
                SleepRating.BAD);


        sessions1 = List.of(
                session1, session2);
        sessions2 = List.of(session2, session3);
    }

    @Test
    void owlTest() {
        ChronoTypeViewer ct = new ChronoTypeViewer();
        Assertions.assertEquals(ChronoType.OWL, ct.apply(sessions1).getResult());
    }

    @Test
    void pigeonTest() {
        ChronoTypeViewer ct = new ChronoTypeViewer();
        Assertions.assertEquals(ChronoType.PIGEON, ct.apply(sessions2).getResult());
    }
}
