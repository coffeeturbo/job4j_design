package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {

    private int pointer;
    private int[] array;

    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        var result = false;
        for (int i = pointer; i < array.length; i++) {
            if (isEven(array[i])) {
                pointer = i;
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return array[pointer++];
    }

    private static boolean isEven(int value) {
        return value % 2 == 0;
    }
}
