package ru.yandex.practicum.sleeptracker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final String SESSIONS_FILE_NAME = "src/main/resources/sleep_log.txt";
    private static final DateTimeFormatter LOG_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final String SEPARATOR = ";";


    private final List<Function<List<SleepingSession>, SleepAnalysisResult>> ANALYTIC_FUNCTIONS = List.of(
            new InsomniaAnalysis(),
            new BadSleepCounter(),
            new NightsCounter(),
            new ChronoTypeViewer(),
            new LongestSession(),
            new ShortestSession(),
            new AverageSession()
    );

    public static void main(String[] args) {
        SleepTrackerApp app = new SleepTrackerApp();
        try {
            List<SleepingSession> sessions = app.readFile(app.getFile(SESSIONS_FILE_NAME));

            List<SleepAnalysisResult> results = app.analyzeSessions(sessions);
            results.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<SleepAnalysisResult> analyzeSessions(List<SleepingSession> sessions) {
        return ANALYTIC_FUNCTIONS.stream()
                .map(it -> it.apply(sessions))
                .toList();
    }


    private File getFile(String filename) throws FileNotFoundException {
        Path filePath = Paths.get(filename);
        File file = filePath.toFile();
        if (!file.exists()) {
            throw new FileNotFoundException("Такого файла не существует");
        }

        return file;
    }

    private List<SleepingSession> readFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(fileReader)) {
            return reader.lines()
                    .map(this::parseLine)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    private Optional<SleepingSession> parseLine(String line) {
        try {
            String[] parts = line.split(SEPARATOR);
            LocalDateTime start = LocalDateTime.parse(parts[0].trim(), LOG_FORMATTER);
            LocalDateTime end = LocalDateTime.parse(parts[1].trim(), LOG_FORMATTER);
            SleepRating quality = SleepRating.valueOf(parts[2].trim().toUpperCase());

            if (start.isAfter(end)) {
                end = end.plusDays(1);
            }

            return Optional.of(new SleepingSession(start, end, quality));


        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }


    }
}