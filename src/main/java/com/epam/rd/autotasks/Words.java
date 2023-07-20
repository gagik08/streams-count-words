package com.epam.rd.autotasks;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        String[] words = String.join("\n", lines).toLowerCase().replaceAll("[^\\p{L}\\p{N}]+", " ").split("\\b");

        Map<String, Long> wordCounts = Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        Map<String, Long> filteredWords = wordCounts.entrySet().stream()
                .filter(entry -> entry.getKey().length() >= 4 && entry.getValue() >= 10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Map.Entry<String, Long>> sortedWords = filteredWords.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());

        return sortedWords.stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
