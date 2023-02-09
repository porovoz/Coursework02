package com.skypro.course2.chapter1.task;

import java.time.LocalDate;

public class MonthlyTask extends Task {

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.getDayOfMonth() == getDateTime().toLocalDate().getDayOfMonth();
    }

    @Override
    public boolean contains(String substr) {
        return super.contains(substr);
    }
}
