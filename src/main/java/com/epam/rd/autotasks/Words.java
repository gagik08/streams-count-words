package com.epam.rd.autotasks;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Words {

    public static String countWords(List<String> lines) {
//
//        Map<String, Long> frequency = new HashMap<>();
//        Predicate<String> lengthIsLessThan4 = word -> word.length() >= 4;
//        frequency = words.stream()
//                .filter(lengthIsLessThan4)
//                .map(word -> new AbstractMap.SimpleEntry<>(word.toLowerCase(), 1))
//                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));
//
//        Map<String, Long> finalFrequency = frequency.entrySet().stream()
//                .filter(entry -> entry.getValue() >= 10)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//
//        LinkedHashMap<String, Long> sortedFrequency = finalFrequency.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue,
//                        LinkedHashMap::new
//                ));
//
//        StringBuilder stringBuilder = new StringBuilder();
//        sortedFrequency.forEach(
//                (word, count) -> stringBuilder.append(word).append(" - ").append(count).append('\n')
//        );
//
//        return stringBuilder.toString().trim();

        Predicate<String> lengthIsEqualOrMoreThan4 = (word) -> word.length() >= 4;
        List<String> words = lines.stream()
                .flatMap(str -> Arrays.stream(str.replaceAll("[^\\p{L}\\p{N}]+", " ").trim().split("\\s+")))
                .filter(lengthIsEqualOrMoreThan4)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        Map<String,Long> frequency = words.stream()
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
