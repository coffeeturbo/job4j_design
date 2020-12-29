package threads;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName());
            }
        );
        first.start();

        Thread second = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName());
            }
        );
        second.start();

        while ((first.getState() != Thread.State.TERMINATED)
            && (second.getState() != Thread.State.TERMINATED)) {
            System.out.printf("%s: %s \n", first.getName(), first.getState());
            System.out.printf("%s: %s \n", second.getName(), second.getState());
        }

        System.out.printf("%s: %s \n", first.getName(), first.getState());
        System.out.printf("%s: %s \n", second.getName(), second.getState());

        if ((first.getState() == Thread.State.TERMINATED)
            && (second.getState() == Thread.State.TERMINATED)) {
            System.out.println("Executing of Threads has done");
        }
    }
}
