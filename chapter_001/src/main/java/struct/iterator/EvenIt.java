package struct.iterator;

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
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Object next() throws NoSuchElementException {
        Object result;
        do {
            if (pointer >= array.length) {
                throw new NoSuchElementException();
            }
            result = array[pointer++];

        } while (!isEven((int) result));

        return result;
    }

    private static boolean isEven(int value) {
        return value % 2 == 0;
    }
}
