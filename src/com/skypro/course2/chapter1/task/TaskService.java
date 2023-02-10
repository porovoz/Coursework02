package com.skypro.course2.chapter1.task;

import com.skypro.course2.chapter1.enums.TaskPeriodicity;
import com.skypro.course2.chapter1.exceptions.IncorrectArgumentException;
import com.skypro.course2.chapter1.exceptions.TaskNotFoundException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Set<Task> removedTasks = new HashSet<>();

    public void add(TaskPeriodicity taskPeriodicity) throws IncorrectArgumentException {
        var tp = taskPeriodicity.createTask();
        tp.askData();
        taskMap.putIfAbsent(tp.getId(), tp);
        System.out.println("Task created: " + tp);
    }

    public void remove(int id) throws TaskNotFoundException {
        Task task = taskMap.get(id);
        removedTasks.add(task);
 //       taskMap.remove(id); // you can use this string instead of next to remove the task
        taskMap.values().removeIf(t -> t.getId() == id);
        System.out.printf("Task '%s' was removed and placed in archive \n", task);
        if (task == null) {
            throw new TaskNotFoundException("There is no task with this id: " + id);
        }
    }

    public void getAllByDate(LocalDate localDate) throws TaskNotFoundException {
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            LocalDate taskDate = taskMap.getValue().getDateTime().toLocalDate();
            if (taskDate.equals(localDate)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            }
            else if (localDate.isAfter(taskDate) && taskMap.getValue().appearsIn(localDate)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            } else throw new TaskNotFoundException("There is no any task for that date");
        }
    }

    public void listTaskMap() {
        System.out.println("Actual task list: ");
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            System.out.println(taskMap.getKey() + " " + taskMap.getValue());
        }
    }

    public void listRemovedTasks() {
        System.out.println("Removed task list: ");
        removedTasks.forEach(System.out::println);
        if (removedTasks.size() == 0) {
            System.out.println("There is no removed tasks");
        }
    }

    public void updateTitle(int id) {
        var taskTitle = InputUtils.askString("Enter new title");
        taskMap.get(id).setTitle(taskTitle);
        System.out.printf("The title for the task with ID: %d was updated to: %s \n",
                id, taskMap.get(id).getTitle());
    }

    public void updateDescription(int id) {
        var taskDescription = InputUtils.askString("Enter new description");
        taskMap.get(id).setDescription(taskDescription);
        System.out.printf("The description for the task with ID: %d was updated to: %s \n",
                id, taskMap.get(id).getDescription());
    }

    public void groupByDate() {
        System.out.println("Available tasks grouped by date: ");
        Comparator<Map.Entry<Integer, Task>> myComparator = Comparator.comparing(o -> o.getValue().getDateTime());
        Map<Integer, Task> sortedMap = taskMap.entrySet().stream()
                .sorted(myComparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
        sortedMap.entrySet().forEach(System.out::println);
    }

    public void findTask(String substr) {
        var tmp = substr.toLowerCase();
        taskMap.values().stream()
                .filter(task -> task.contains(tmp))
                .forEach(System.out::println);
    }
}
