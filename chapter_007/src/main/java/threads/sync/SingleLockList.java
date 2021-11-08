package threads.sync;

import list.DynamicList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("list")
    private final DynamicList<T> list = new DynamicList<>();

    public void add(T elem) {
        synchronized (list) {
            list.add(elem);
        }
    }

    @Override
    public  Iterator<T> iterator() {
        synchronized (list) {
            return copy(list).iterator();
        }
    }

    private synchronized DynamicList<T> copy(DynamicList<T> iterator) {
        DynamicList<T> newList = new DynamicList<>();
        iterator.forEach(newList::add);
        return newList;
    }

}
