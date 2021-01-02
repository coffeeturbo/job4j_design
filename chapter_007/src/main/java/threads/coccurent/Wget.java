package threads.coccurent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                if (bytesRead > speed) {
                    System.out.println(bytesRead);
                    int pauseTime = ((bytesRead - speed) / speed) * 1000;
                    System.out.println(pauseTime);
                    Thread.sleep(pauseTime);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
//        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int speed = Integer.parseInt(args[1]);
//        int speed = 512;
        Thread wget = new Thread(new threads.Wget(url, speed));
        wget.start();
        wget.join();
    }
}
