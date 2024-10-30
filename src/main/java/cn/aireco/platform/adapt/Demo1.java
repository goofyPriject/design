package cn.aireco.platform.adapt;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Demo1 {

    public static void main(String[] args) {
//        long delay = System.currentTimeMillis();
//        System.out.println(delay);
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
//        executor.schedule(() -> {
//            System.out.println("run task");
//            long end = System.currentTimeMillis()-delay;
//            System.out.println(end/1000);
//        }, 5, TimeUnit.SECONDS);

        System.out.println(LocalDate.now());
    }


    private static Predicate<Object> shouldRetry() {
        return (t) -> t.equals("a");
    }

    private static void test(Predicate<Object> predicate) {
        if (predicate.test("a")) {
            System.out.println("-----");
        }
    }

    private static void test1(Consumer<Object> consumer) {
        consumer.accept("a");
    }

    private static Consumer<Object> consumer() {
        return (t) -> {
            boolean test = shouldRetry().test(t);
            System.out.println("dddd"+test);
        };
    }
}
