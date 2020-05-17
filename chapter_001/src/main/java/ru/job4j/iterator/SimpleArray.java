package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;
    private int size = 0;

    SimpleArray(int size) {
        objects = new Object[size];
    }

    public void add(T model) {
        this.objects[size++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);

        this.objects[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) this.objects[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(objects, index + 1, objects, index, size - index);
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer = 0;

            @Override
            public boolean hasNext() {
                return objects.length > pointer;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) objects[pointer++];
            }
        };
    }
}
