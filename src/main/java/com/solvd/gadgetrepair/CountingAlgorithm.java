package com.solvd.gadgetrepair;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingAlgorithm {

    public static void main(String[] args) {
        String[] givenWords = {"social", "Durkheim", "world", "between"};
        Map<String, Integer> wordCount = new HashMap<>();

        try {
            List<String> lines = FileUtils.readLines(new File("src/main/resources/bourdieu.txt"));

            for (String line : lines) {
                String[] words = StringUtils.split(line);
                for (String word : words) {
                    if (isGivenWord(word, givenWords)) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
            writeOutput(wordCount, "src/main/resources/wordcount.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeOutput(Map<String, Integer> wordCount, String outputFile) throws IOException {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " = " + entry.getValue() + "\n");
            }
        }
    }

    private static boolean isGivenWord(String word, String[] givenWords) {
        for (String givenWord : givenWords) {
            if (word.equals(givenWord)) {
                return true;
            }
        }
        return false;
    }
}