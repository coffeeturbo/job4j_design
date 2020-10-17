package tdd;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = (Integer arg) -> arg > 0;

        return testInLoop(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = (Integer arg) -> arg < 0;

        return testInLoop(value, comparator, predicate);
    }

    private  <T> T testInLoop(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T result = value.get(0);
        for (T elem: value) {
            if (predicate.test(comparator.compare(elem, result))) {
                result = elem;
            }
        }

        return result;
    }

}
