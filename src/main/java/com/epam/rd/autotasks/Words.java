package com.epam.rd.autotasks;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;


public class Words {

    public String countWords(List<String> lines) {
        Predicate<String> lengthIsEqualOrMoreThan4 = (word) -> word.length() >= 4;
        List<String> words = lines.stream()
                .flatMap(str -> Arrays.stream(str.split("[^\\p{L}\\p{N}]+")))
                .filter(lengthIsEqualOrMoreThan4)
                .map(String::toLowerCase)
                .collect(toList());


        Predicate<Map.Entry<String, Long>> occuresMoreOrEqualTo10Times = entry -> entry.getValue() >= 10;
        Map<String,Long> frequency = words.stream()
                .collect(groupingBy(word -> word, counting()))
                .entrySet().stream()
                .filter(occuresMoreOrEqualTo10Times)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        StringBuilder stringBuilder = new StringBuilder();
        frequency.forEach((word, count) -> stringBuilder.append(word).append(" - ").append(count).append('\n'));
        return stringBuilder.toString().trim();
    }
}
