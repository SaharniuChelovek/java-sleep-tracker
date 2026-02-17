package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class SleepTrackerAppTest {

    static SleepTrackerApp sleepTrackerApp;
    static String filename;
    static String exampleLine;

    @BeforeAll
    static void sleepTracker() {
        sleepTrackerApp = new SleepTrackerApp();
        filename = "src/main/resources/sleep_log.txt";
        exampleLine = "07.10.25 23:45;08.10.25 06:30;GOOD";
    }

    @Test
    void getFileTest() throws FileNotFoundException {
        File file = sleepTrackerApp.getFile(filename);

        Assertions.assertNotNull(file);
        Assertions.assertTrue(file.exists());
    }

    @Test
    void emptyFileTest() {
        Assertions.assertThrows(FileNotFoundException.class,
                () -> sleepTrackerApp.getFile(""));
    }

    @Test
    void readFileTest() throws IOException {
        List<SleepingSession> sessions = sleepTrackerApp.readFile(sleepTrackerApp.getFile(filename));
        Assertions.assertEquals(13, sessions.size());
    }

    @Test
    void parseLineTest() {
        Optional<SleepingSession> result = sleepTrackerApp.parseLine(exampleLine);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(SleepRating.GOOD, result.get().rating());
    }

    @Test
    void endAfterStartTest() {
        String line = "11.10.25 23:10;11.10.25 07:00;GOOD";

        Optional<SleepingSession> result = sleepTrackerApp.parseLine(line);

        Assertions.assertTrue(result.isPresent());

        SleepingSession session = result.get();
        Assertions.assertTrue(session.end().isAfter(session.start()));
    }

}