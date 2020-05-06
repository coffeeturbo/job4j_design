package struct.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            Iterator<Integer> iterator = it.next();

            @Override
            public boolean hasNext() {
                if (!iterator.hasNext()) {
                    while (it.hasNext()) {
                        iterator = it.next();
                        if (iterator.hasNext()) {
                            break;
                        }
                    }
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
