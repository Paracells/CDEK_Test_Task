package ru.paracells.utils;

import ru.paracells.call.model.Task;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomTask {

    private static Task task;

    public static Task generate() {
        task = new Task();
        task.setNumber(Math.abs(ThreadLocalRandom.current().nextInt(1, 2)));
        task.setDate(GenerateRandomDateTime.generateDate());
        task.setTime(GenerateRandomDateTime.generateTime());
        task.setComplete(ThreadLocalRandom.current().nextInt() * 10 > 5);
        return task;
    }

    private GenerateRandomTask() {
    }
}
