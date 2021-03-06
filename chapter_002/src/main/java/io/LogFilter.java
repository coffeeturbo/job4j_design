package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return log;
    }

    public static void save(List<String> log, String file) {

        try (PrintWriter out = new PrintWriter(
            new BufferedOutputStream(
                new FileOutputStream(file)
            ))) {

            log.forEach(s -> out.write(s.concat(System.lineSeparator())));

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "result_log.txt");
        System.out.println(log);
    }
}
