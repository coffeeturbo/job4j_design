package io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {

            String str = "";
            for (int i = 0; i <= 10; i++) {
                for (int y = 0; y <= 10; y++) {
                    int rsl = i * y;
                    str = str.concat(String.format("%d * %d = %d \n", i, y, rsl));
                }
            }

            out.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
