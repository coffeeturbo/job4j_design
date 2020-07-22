package io.console;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final Integer STOP = 0;
    private static final Integer PLAY = 1;
    private static final Integer PAUSE = 2;

    public void init(Input in, String fetchFile) {

        Integer action = ConsoleChat.PLAY;
        do {
            consoleOutput("SYSTEM", "Введите слово-фразу");
            String word = in.getInput();
            consoleOutput("YOU", word);
            action = switchAction(action, word);

            if (!action.equals(ConsoleChat.PAUSE) && !action.equals(ConsoleChat.STOP)) {
                consoleOutput("FRIEND", fetchWordFromFile(fetchFile));
            }

        } while (!action.equals(ConsoleChat.STOP));
    }


    public static void main(String[] args) {

        ConsoleChat chat = new ConsoleChat();
        chat.init(new ConsoleInput(), "./chapter_002/data/chat_words.txt");

    }

    public static String fetchWordFromFile(String file) {

        List<String> words = new ArrayList<>();
        try (BufferedReader out = new BufferedReader(new FileReader(file))) {
            out.lines().forEach(words::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int index = new Random().nextInt(words.size());

        return words.get(index);
    }

    static private void consoleOutput(String source, String word) {
        String outputStr = String.format("[%6s]: %s", source, word);

        write("./chapter_002/data/chat_log.txt", outputStr);
        System.out.println(outputStr);
    }

    static private Integer switchAction(Integer action, String word) {
        String actionWord = word.trim().toLowerCase();

        if (action.equals(ConsoleChat.PAUSE)
            && actionWord.equals("продолжить")) {
            action = ConsoleChat.PLAY;
        } else if (actionWord.equals("стоп")) {
            action = ConsoleChat.PAUSE;
        }

        if (actionWord.equals("закончить")) {
            action = ConsoleChat.STOP;
        }

        return action;
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
