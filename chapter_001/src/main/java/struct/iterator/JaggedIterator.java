package struct.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedIterator implements Iterator {

    private int iPointer;
    private int jPointer;
    final private int[][] array;

    public JaggedIterator(int[][] array) {
        jPointer = 0;
        iPointer = 0;
        this.array = array;
    }

    public boolean hasNext() {
        return iPointer < array.length && jPointer < array[iPointer].length;
    }

    public Object next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        var elem = array[iPointer][jPointer];

        if (jPointer == array[iPointer].length - 1) {
            jPointer = 0;
            iPointer++;
        } else {
            jPointer++;
        }

        return elem;
    }
}
