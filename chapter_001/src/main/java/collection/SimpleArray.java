package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container = new Object[1];
    private int size = 0;
    private int modCounder = 0;

    @Override
    public Iterator<T> iterator() {
        this.modCounder++;

        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) container[pointer++];
            }
        };
    }

    public T get(int index) {
        Objects.checkIndex(index, size);

        return (T) container[index];
    }

    public void add(T model) {
        if (modCounder > 0) {
            throw new ConcurrentModificationException();
        }

        if (size >= container.length - 1) {
            resizeContainer(10);
        }

        container[size++] = model;
    }

    private void resizeContainer(int increaseSize) {
        Object[] newContainer = new Object[container.length + increaseSize];
        System.arraycopy(container, 0, newContainer, 0, size);
        container = newContainer;
    }
}
