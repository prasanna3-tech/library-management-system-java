package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int getLateDays(String dueDate, String currentDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate due = LocalDate.parse(dueDate, formatter);
        LocalDate current = LocalDate.parse(currentDate, formatter);

        if(current.isAfter(due)) {
            return (int) ChronoUnit.DAYS.between(due, current);
        }

        return 0;
    }
}