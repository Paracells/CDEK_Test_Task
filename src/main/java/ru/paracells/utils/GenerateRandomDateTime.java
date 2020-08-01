package ru.paracells.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

/*
generate random date and time for filling database
 */
public class GenerateRandomDateTime {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static LocalDate generateDate() {
        return LocalDate.of(random.nextInt(2017, 2020), random.nextInt(1, 12), random.nextInt(1, 30));

    }

    public static LocalTime generateTime() {
        return LocalTime.of(random.nextInt(0, 23), random.nextInt(0, 59), random.nextInt(0, 59));
    }

    private GenerateRandomDateTime() {
    }
}
