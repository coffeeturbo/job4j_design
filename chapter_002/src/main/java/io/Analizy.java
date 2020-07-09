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
        try (BufferedReader out = new BufferedReader(new FileReader(source))) {
            StringBuilder start = new StringBuilder();
            out.lines()
                .filter(s -> !s.isEmpty())
                .map(s  -> bufferedLine(s, start))
                .filter(s -> !s.isEmpty())
                .forEach(s -> write(target, s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> readFile(String file) {
        List<String> content = new ArrayList<>();

        try (BufferedReader out = new BufferedReader(new FileReader(file))) {
            out.lines()
                .filter(s -> !s.isEmpty())
                .forEach(content::add);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return content;
    }

    private static class Log {
        public Integer status;
        public String time;

        public Log(String log) {
            String[] splitLine = log.split(" ");
            this.status = Integer.parseInt(splitLine[0]);
            this.time = splitLine[1];
        }
    }

    private static String bufferedLine(String s, StringBuilder start) {
        Log logRow = new Log(s);
        String result = "";
        if (start.length() == 0 && (
            logRow.status.equals(HTTP_BAD_REQUEST))
            || logRow.status.equals(HTTP_ERROR)
        ) {
            start.append(logRow.time);
        } else if (start.length() != 0
            && (logRow.status.equals(HTTP_OK)
            || logRow.status.equals(HTTP_REDIRECT))) {
            start.append(";" + logRow.time);
            result = start.toString();
            start.delete(0, start.length());
        }

        return result;
    }

    private static boolean write(String file, String content) {
        try (PrintWriter out = new PrintWriter(
            new FileOutputStream(file, true))) {
            out.println(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
