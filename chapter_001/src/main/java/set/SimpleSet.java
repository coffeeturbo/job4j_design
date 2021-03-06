package set;

import ru.job4j.iterator.SimpleArray;

import java.util.Iterator;


public class SimpleSet<E> implements Iterable<E> {

    private SimpleArray<E> set;

    SimpleSet() {
        set = new SimpleArray<>(10);
    }
    public void add(E model) {
        if (!hasElem(model)) {
            set.add(model);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    public boolean hasElem(E model) {
        for (E elm : set) {
            if (elm == model
                || elm.equals(model)) {
                return true;
            }
        }
        return false;
    }
}
