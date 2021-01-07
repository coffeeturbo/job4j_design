package threads.sync;

import list.DynamicList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final DynamicList<T> list = new DynamicList<>();

    public synchronized void add(T elem) {
        list.add(elem);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized DynamicList<T> copy(DynamicList<T> iterator) {
        DynamicList<T> newList = new DynamicList<>();
        iterator.forEach(newList::add);
        return newList;
    }

}
