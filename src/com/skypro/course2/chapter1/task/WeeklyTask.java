package com.skypro.course2.chapter1.task;

import java.time.LocalDate;

public class WeeklyTask extends Task {

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.getDayOfWeek().equals(getDateTime().toLocalDate().getDayOfWeek());
    }
}
