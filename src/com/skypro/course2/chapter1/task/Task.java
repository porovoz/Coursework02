package com.skypro.course2.chapter1.task;

import com.skypro.course2.chapter1.enums.Type;
import com.skypro.course2.chapter1.exceptions.IncorrectArgumentException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task {

    private static int idGenerator = 0;
    private String title;
    private Type type;
    private final int id;
    private LocalDateTime dateTime;
    private String description;

    public Task() {
        idGenerator++;
        id = idGenerator;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return String.format("id: %d, type: %s, title: %s, description: %s, date and time: %s", id, type, title,
                description, InputUtils.dateTimeToString(dateTime));
    }

    public void askData() throws IncorrectArgumentException {
        System.out.println("Please select task type:");
        for (Type taskType : Type.values()) {
            System.out.println(taskType);
        }
        type = Type.valueOf(InputUtils.askString("Your selection").toUpperCase());
        title = InputUtils.askString("Title");
        if (title == null || title.isBlank() || title.isEmpty()) {
            throw new IncorrectArgumentException("Enter the correct title");
        }
        description = InputUtils.askString("Enter a description");
        if (description == null || description.isBlank() || description.isEmpty()) {
            throw new IncorrectArgumentException("Enter the correct description");
        }
        dateTime = InputUtils.askDateTime("Enter the date and time");
    }

    public abstract boolean appearsIn(LocalDate localDate);

    public boolean contains(String substr) {
        var strId = String.valueOf(id);
        var strType = String.valueOf(type);
        return strId.contains(substr)
                || title.toLowerCase().contains(substr)
                || strType.toLowerCase().contains(substr)
                || InputUtils.dateTimeToString(dateTime).toLowerCase().contains(substr)
                || description.toLowerCase().contains(substr);
    }
}

