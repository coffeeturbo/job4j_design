package io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                char readChar = (char) read;
                text.append(readChar);
            }

            // System.lineSeparator() не корректрно срабатыват на маке
            String[] words = text.toString().split("\r");
            for (String word : words) {

                if (Integer.parseInt(word) % 2 == 0) {
                    System.out.println(word);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
