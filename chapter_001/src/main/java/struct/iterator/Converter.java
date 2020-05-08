package struct.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> iterator = it.hasNext() ? it.next() : Collections.emptyIterator();

            @Override
            public boolean hasNext() {
                while (!iterator.hasNext() && it.hasNext()) {
                    iterator = it.next();
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return iterator.next();
            }
        };
    }
}
