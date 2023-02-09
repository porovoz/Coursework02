package com.skypro.course2.chapter1;

import com.skypro.course2.chapter1.enums.TaskPeriodicity;
import com.skypro.course2.chapter1.exceptions.IncorrectArgumentException;
import com.skypro.course2.chapter1.exceptions.TaskNotFoundException;
import com.skypro.course2.chapter1.task.InputUtils;
import com.skypro.course2.chapter1.task.TaskService;

import java.util.Scanner;

public class Main {
    private static final TaskService taskService = new TaskService();
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("Please select a command: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            add();
                            break;
                        case 2:
                            remove();
                            break;
                        case 3:
                            getAllByDate();
                            break;
                        case 4:
                            listTaskMap();
                            break;
                        case 5:
                            listRemovedTasks();
                            break;
                        case 6:
                            updateTitle();
                            break;
                        case 7:
                            updateDescription();
                            break;
                        case 8:
                            groupByDate();
                            break;
                        case 9:
                            findTask();
                            break;
                        case 0:
                            break label;
                        default:
                            System.out.println("Unknown menu item");
                    }
                    } else {
                    scanner.next();
                    System.out.println("Please select a menu item from the list!");
                }
            }
            System.out.println("Good bye!");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getArgument());
        }
    }

    private static void printMenu() {
        System.out.println("Available commands: \n 1. Add a task \n 2. Delete a task " +
                "\n 3. Get a task for the specified date \n 4. Show task list \n 5. Show removed task list " +
                "\n 6. Update task title \n 7. Update task description \n 8. Group all tasks by date " +
                "\n 9. Find task \n 0. Exit");
    }

    private static void add() throws IncorrectArgumentException {
        System.out.println("Please select task periodicity: ");
        for (TaskPeriodicity taskPeriodicity : TaskPeriodicity.values()) {
            System.out.println(taskPeriodicity);
        }
        var strPeriodicity = InputUtils.askString("Your selection").toUpperCase();
        var periodicity = TaskPeriodicity.valueOf(strPeriodicity);
        taskService.add(periodicity);
    }

    private static void remove() throws TaskNotFoundException {
        int id = InputUtils.askInt("Enter task ID you want to remove");
        taskService.remove(id);
    }

    private static void getAllByDate() throws TaskNotFoundException {
        var date = InputUtils.askDate("Please enter the date");
        taskService.getAllByDate(date);
    }

    private static void listTaskMap() {
        taskService.listTaskMap();
    }

    private static void listRemovedTasks() {
        taskService.listRemovedTasks();
    }

    private static void updateTitle() {
        var id = InputUtils.askInt("Please enter task ID which title you want to update");
        taskService.updateTitle(id);
    }

    private static void updateDescription() {
        var id = InputUtils.askInt("Please enter task ID which description you want to update");
        taskService.updateDescription(id);
    }

    private static void groupByDate() {
        taskService.groupByDate();
    }

    private static void findTask() {
        var substr = InputUtils.askString("Enter substring to find");
        taskService.findTask(substr);
    }
}