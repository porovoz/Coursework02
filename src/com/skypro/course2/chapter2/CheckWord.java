package com.skypro.course2.chapter2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Map.Entry.comparingByKey;

public class CheckWord {
    public static void main(String[] args) {
        String inputString = null;
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        while (isNullString(inputString)) {
            System.out.println("Please enter the text you want to check: \n");
            input(inputString = scanner.next());
        }
    }

    public static boolean isNullString(String input) {
        return (input == null || input.isEmpty() || input.isBlank());
    }

    public static void input(String inputString) {
        Stream<String> countWords = new ArrayList<>(List.of(inputString.split(" "))).stream();
        System.out.printf("There are %s words in text \n", countWords.count());
        System.out.println("TOP 10 words:");
        Stream<String> inputStringStream = new ArrayList<>(List.of(inputString.split(" "))).stream();
        Map<String, Long> countWordsMap = inputStringStream
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        countWordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(comparingByKey()))
                .limit(10)
                .forEach(System.out::println);
    }
}
