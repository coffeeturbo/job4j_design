package list;

public class SimpleQueue<T>  {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        return getOut().pop();
    }

    public void push(T value) {
        in.push(value);
    }

    private SimpleStack<T> getOut() {
        while (in.getSize() > 0) {
            T el = in.pop();
            out.push(el);
        }
        return out;
    }
}
