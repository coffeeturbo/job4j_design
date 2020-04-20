package struct.iterator;

import java.util.Iterator;

public class JaggedIterator implements Iterator {

    private static int pointer;
    private int[][] array;
    private int arrayLength;

    public JaggedIterator(int[][] array) {
        pointer = 0;
        this.array = array;
        this.arrayLength = countElements(array);
    }

    public boolean hasNext() {
        return pointer < arrayLength;
    }

    public Object next() {

        Object element = null;
        var localPointer = 0;

        outerLoop:
        for (int i = 0; i < array.length; i++) {
            for (int n = 0; n < array[i].length; n++) {
                if (localPointer == pointer) {
                    element = array[i][n];
                    pointer++;
                    break outerLoop;
                }

                localPointer++;
            }
        }

        return element;
    }

    private static int countElements(int[][] array) {
        int arraySize = 0;
        for (int[] ints : array) {
            for (int val: ints) {
                arraySize++;
            }
        }
        return arraySize;
    }
}
