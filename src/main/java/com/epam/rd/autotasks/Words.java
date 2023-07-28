package com.epam.rd.autotasks;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Words {

    public static String countWords(List<String> lines) {
        Predicate<String> lengthIsEqualOrMoreThan4 = (word) -> word.length() >= 4;
        List<String> words = lines.stream()
                .flatMap(str -> Arrays.stream(str.replaceAll("[^\\p{L}\\p{N}]+", " ").trim().split("\\s+")))
                .filter(lengthIsEqualOrMoreThan4)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Map<String, Long> frequency = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= 10)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        StringBuilder stringBuilder = new StringBuilder();
        frequency.forEach((word, count) -> stringBuilder.append(word).append(" - ").append(count).append('\n'));
        return stringBuilder.toString().trim();
    }
}
