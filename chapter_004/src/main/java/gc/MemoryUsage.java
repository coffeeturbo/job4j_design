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


    /**
     *
     * 1. Используя разные ключи запуска виртуальной машины установить различные виды сборщика мусора.
     * [0.013s][info][gc] Using G1
     * [0.052s][info][gc] Periodic GC disabled
     * [0.153s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 1M->1M(4M) 1.549ms
     * [0.239s][info][gc] GC(1) Pause Young (Concurrent Start) (G1 Evacuation Pause) 2M->2M(4M) 3.209ms
     * [0.239s][info][gc] GC(3) Concurrent Cycle
     * [0.245s][info][gc] GC(2) Pause Full (G1 Evacuation Pause) 2M->1M(4M) 5.597ms
     * [0.245s][info][gc] GC(3) Concurrent Cycle 5.711ms
     * [0.323s][info][gc] GC(4) Pause Full (System.gc()) 2M->1M(4M) 8.842ms
     * ========
     * [0.011s][info][gc] Using Serial
     * [0.128s][info][gc] GC(0) Pause Young (Allocation Failure) 1M->0M(3M) 2.825ms
     * [0.206s][info][gc] GC(1) Pause Young (Allocation Failure) 1M->1M(3M) 2.375ms
     * [0.279s][info][gc] GC(2) Pause Full (System.gc()) 2M->1M(3M) 4.330ms
     * ========
     * [0.015s][info][gc] Using Parallel
     * [0.067s][info][gc] GC(0) Pause Young (Allocation Failure) 0M->0M(3M) 1.292ms
     * [0.137s][info][gc] GC(1) Pause Young (Allocation Failure) 0M->0M(3M) 1.456ms
     * [0.186s][info][gc] GC(2) Pause Young (Allocation Failure) 1M->0M(3M) 1.266ms
     * [0.229s][info][gc] GC(3) Pause Young (Allocation Failure) 1M->0M(3M) 1.554ms
     * [0.266s][info][gc] GC(4) Pause Young (Allocation Failure) 1M->1M(3M) 2.143ms
     * [0.347s][info][gc] GC(5) Pause Young (Allocation Failure) 1M->1M(3M) 1.283ms
     * [0.363s][info][gc] GC(6) Pause Young (System.gc()) 1M->1M(3M) 1.240ms
     * [0.371s][info][gc] GC(7) Pause Full (System.gc()) 1M->1M(3M) 7.896ms
     * ========
     * Concurrent Mark Sweep (CMS) support removed in 14.0
     *
     * 2. Оценить поведения срабатывания различных типов сборщиков мусора для программы из первого урока данной модуля
     * G1 - часто вызывает young and old сборщики мусора
     * время работы 8.842ms
     * Serial - гораздое реже запускает сборщики мусора
     * время работы 4.330ms
     * Parallel - чаще всех вызывает чистку young обьектов ну и в самом конце одну полную чистку
     * время работы 7.896ms
     *
     *
     * 3. Как вы считаете какой из сборщиков мусора подойдет наиболее оптимально для приложения заявок из второго модуля?
     *      Parallel
     * 4. Какой тип сборщика будет оптимален для сервеного приложения?
     *  Concurrent Mark Sweep (CMS) или Garbage-First (G1)
     *
     */
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
