package threads.coccurent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                var i = 0;
                while (i <= 100) {
                    Thread.sleep(1000);
                    System.out.printf("\r Loading: %s%%", i);
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
