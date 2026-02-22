package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class InsomniaAnalysis implements Function<List<SleepingSession>, SleepAnalysisResult> {

    static final String FUNCTION_TITLE = "Количество бессонных ночей";

    NightSessionUtils nsu = new NightSessionUtils();

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(FUNCTION_TITLE, 0L);
        }

        // 1. Определяем период логирования
        LocalDateTime firstStart = sessions.get(0).start();
        LocalDateTime lastEnd = sessions.get(sessions.size() - 1).end();

        LocalDate startDate = firstStart.toLocalDate();
        LocalDate endDate = lastEnd.toLocalDate();

        // 2. Считаем общее число ночей
        long totalNights = Period.between(startDate, endDate).getDays();

        // Учитываем первую ночь, если сон начался до 12:00
        if (firstStart.getHour() < 12) {
            totalNights++;
        }

        // 3. Считаем ночи со сном
        Set<LocalDate> sleptNights = new HashSet<>();

        for (SleepingSession session : sessions) {
            if (!nsu.isNightSession(session)) {
                continue;
            }

            // Определяем, к какой ночи относится сон
            LocalDate nightDate = determineNightDate(session);
            sleptNights.add(nightDate);
        }

        // 4. Вычисляем бессонные ночи
        long sleeplessNights = totalNights - sleptNights.size();

        return new SleepAnalysisResult(FUNCTION_TITLE, sleeplessNights);
    }

    //не совсем понял суть этого метода, сон же всегда по сути относится к дате end, разве нет?
    private LocalDate determineNightDate(SleepingSession session) {

        LocalDateTime start = session.start();
        LocalDateTime end = session.end();

        if (end.isBefore(start)) {
            end = end.plusDays(1);
        }

        return end.toLocalDate();
    }
}
/*я не знаю, может я все это время неправильно понимал суть задания. Я думал от ночей между предпоследней и последней
 * записью надо избавиться, а в итоге используется метод between, который их считает
 * может и не надо было от них избавляться
 * я три недели сижу за 8 спринтом
 * я провалил все мягкие дедлайны
 * я провалил жетский дедлайн
 * я уже взял перенос жесткого дедлайна на 2 недели, чтоб вложить в них 9 спринт, но я ВСЕ ЕЩЕ не могу сдать 8
 * я уже не уверен, что успею
 * а при всем этом надо как-то в институте еще учится
 * меня этот проект уже с ума сводит
 * я не знаю почему не получается
 * */
