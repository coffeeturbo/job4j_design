package io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    static private final int HTTP_OK = 200;
    static private final int HTTP_REDIRECT = 300;
    static private final int HTTP_BAD_REQUEST = 400;
    static private final int HTTP_ERROR = 500;

    public void unavailable(String source, String target) {
        List<String> log = readFile(source);

        ArrayList<String> filtered = new ArrayList<>();
        StringBuilder start = new StringBuilder();
        for (String line : log) {
            if (line.isEmpty()) {
                continue;
            }

            Log logRow = new Log(line);
            if (start.length() == 0 && (
                logRow.status.equals(HTTP_BAD_REQUEST))
                || logRow.status.equals(HTTP_ERROR)
            ) {
                start.append(logRow.time);
            } else if (start.length() != 0
                && (logRow.status.equals(HTTP_OK)
                || logRow.status.equals(HTTP_REDIRECT))) {
                start.append(";" + logRow.time);
                filtered.add(start.toString());
                start = new StringBuilder();
            }
        }

        write(target, filtered);
    }

    public Log getLog(String line) {
        Log log = null;

        if (!line.isEmpty()) {
            log = new Log(line);
        }
        return log;
    }

    public static class Log {
        public Integer status;
        public String time;

        public Log(String log) {
            String[] splitLine = log.split(" ");
            this.status = Integer.parseInt(splitLine[0]);
            this.time = splitLine[1];
        }

        public Log(Integer status, String time) {
            this.status = status;
            this.time = time;
        }

    }

    public static List<String> readFile(String file) {
        List<String> content = new ArrayList<>();

        try (BufferedReader out = new BufferedReader(new FileReader(file))) {
            out.lines().forEach(content::add);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return content;
    }

    public static void write(String file, List<String> content) {
        try (PrintWriter out = new PrintWriter(
            new FileOutputStream(file))) {
            content.forEach(out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
