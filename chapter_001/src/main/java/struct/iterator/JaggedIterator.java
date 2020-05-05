package struct.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedIterator implements Iterator {

    private int i = 0;
    private int j = 0;
    private int[][] data;

    public JaggedIterator(int[][] array) {
        this.data = array;
    }

    public boolean hasNext() {
        if (data.length == 0) {
            return false;
        }

        while (data[i].length == 0 && i < data.length - 1) {
            i++;
        }

        return data[i].length != 0 || i != data.length - 1;
    }

    public Object next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int row = i;
        int col = j;

        if (j < data[i].length - 1) {
            j++;
        } else {
            j = 0;
            i++;
        }

        return data[row][col];
    }
}
