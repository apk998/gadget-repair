package com.solvd.gadgetrepair;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class CountingAlgorithm {

    public static void main(String[] args) {
        String[] words = {"social", "Durkheim", "world", "between"};

        try {
            String filetext = FileUtils.readFileToString(new File("src/main/resources/bourdieu.txt"), "UTF-8");
            StringBuilder total = new StringBuilder();

            for (String word : words) {
                int appearances = StringUtils.countMatches(filetext, word);
                String line = word + " = " + appearances + "\n";
                total.append(line);
            }
            FileUtils.write(new File("src/main/resources/wordcount.txt"), total.toString(), "UTF-8");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}