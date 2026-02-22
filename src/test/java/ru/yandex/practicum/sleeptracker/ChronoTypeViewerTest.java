package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ChronoTypeViewerTest {

    ChronoTypeViewer ct = new ChronoTypeViewer();

    @Test
    void owlTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 23, 1),
                        LocalDateTime.of(2025, 2, 21, 9, 2),
                        SleepRating.GOOD
                ),
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 23, 45),
                        LocalDateTime.of(2025, 2, 21, 10, 59),
                        SleepRating.GOOD
                )
        );
        Assertions.assertEquals(ChronoType.OWL, ct.apply(sessions).getResult());
    }

    @Test
    void pigeonTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 23, 23),
                        LocalDateTime.of(2025, 2, 21, 9, 42),
                        SleepRating.GOOD
                ),
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 20, 45),
                        LocalDateTime.of(2025, 2, 21, 6, 59),
                        SleepRating.GOOD
                )
        );
        Assertions.assertEquals(ChronoType.PIGEON, ct.apply(sessions).getResult());
    }

    @Test
    void larkTest() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 20, 45),
                        LocalDateTime.of(2025, 2, 21, 6, 59),
                        SleepRating.GOOD
                ),
                new SleepingSession(LocalDateTime.of(2025, 2, 20, 20, 45),
                        LocalDateTime.of(2025, 2, 21, 6, 59),
                        SleepRating.GOOD
                )
        );
        Assertions.assertEquals(ChronoType.LARK, ct.apply(sessions).getResult());
    }
}
