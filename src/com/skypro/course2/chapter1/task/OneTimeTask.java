package com.skypro.course2.chapter1.task;

import java.time.LocalDate;

public class OneTimeTask extends Task {

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(getDateTime().toLocalDate());
    }
}
