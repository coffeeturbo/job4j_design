package gc;


/**
 *
 * user - 0
 * -------Usage memory-------
 * Total memory 4,000000
 * Free memory 1,876968
 * Used memory 2,123032
 * Max memory 4,000000
 * user - 1
 * -------Usage memory-------
 * Total memory 4,000000
 * Free memory 1,857391
 * Used memory 2,142609
 * Max memory 4,000000
 *
 * OBJECT SIZE WITH FIELDS
 * 1,876968 - 1,857391 = 0.019577
 *
 *
 *-------Usage memory-------
 * Total memory 4,000000
 * Free memory 2,185387
 * Used memory 1,824646
 * Max memory 4,000000
 * -------Usage memory-------
 * Total memory 4,000000
 * Free memory 2,145752
 * Used memory 1,854248
 * Max memory 4,000000
 *
 *
 * OBJECT SIZE WITHOUT FIELDS
 *
 * 2,154358 - 2,134804 = 0.019554
 *
 *
 * -------Usage memory-------
 * Total memory 4,000000
 * Free memory 1,785095
 * Used memory 2,214905
 * Max memory 4,000000
 * user - 11
 * -------Usage memory-------
 * Total memory 4,000000
 * Free memory 2,353828
 * Used memory 1,646172
 * Max memory 4,000000
 * user - 12
 *
 *  AUTO GC INVOLVED WHEN FREE MEMORY < 1,785095 (45% of max memory)
 *
 */
public class MemoryUsage {


    public static class User {
        private int id;
        public String name;

        public User(int id, String name) {
            this.name = name;
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();

            String out = String.format("DESTRUCT user %s", toString());
            System.out.println(out);
        }

        @Override
        public String toString() {
            return this.name + " - " + id;
        }
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 100; i++) {
            User user = new User(i, "user");
            System.out.println(user.toString());
            user = null;
            info();
        }
        System.gc();
        info();
    }
    public static void info() {
        double mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("-------Usage memory-------");
        System.out.printf("Total memory %.6f%n", runtime.totalMemory() / mb);
        System.out.printf("Free memory %.6f%n", runtime.freeMemory() / mb);
        System.out.printf("Used memory %.6f%n", (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.printf("Max memory %.6f%n", runtime.maxMemory() / mb);
    }
}
