package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class LogFilter {
    public static List<String> filter(String file) {
        ArrayList<String> log = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            Predicate<String> filter = Pattern
                .compile("404 ([-\\d]+)$")
                .asPredicate();

            in.lines()
                .filter(filter)
                .forEach(log::add);

        } catch (Exception e) {
            e.getStackTrace();
        }

        return log;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
