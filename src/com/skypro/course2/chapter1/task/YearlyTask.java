package com.skypro.course2.chapter1.task;

import java.time.LocalDate;

public class YearlyTask extends Task {

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.getDayOfYear() == getDateTime().toLocalDate().getDayOfYear();
    }
}
