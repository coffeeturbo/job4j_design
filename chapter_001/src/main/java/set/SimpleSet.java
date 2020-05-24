package set;

import ru.job4j.iterator.SimpleArray;

import java.util.Iterator;


public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> set = new SimpleArray<>(10);

    public void add(E model) {
        for (E elm : set) {
            if (elm.equals(model)) {
                return;
            }
        }
        set.add(model);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
