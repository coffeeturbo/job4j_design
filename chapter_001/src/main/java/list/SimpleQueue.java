package list;

import java.util.NoSuchElementException;

public class SimpleQueue<T>  {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl = null;
        try {
            T el = in.pop();
            while (el != null) {
                out.push(el);
                el = in.pop();
            }
        } catch (NoSuchElementException e) {
            rsl = out.pop();
        }

        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }

}
