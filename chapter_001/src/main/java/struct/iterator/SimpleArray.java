package struct.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;
    private int index = 0;

    SimpleArray(int size) {
        objects = new Object[size];
    }

    public void add(T model) {
        this.objects[index++] = model;
    }

    public void set(int id, T model) {
        Objects.checkIndex(id, index);

        this.objects[id] = model;
    }

    public T get(int id) {
        Objects.checkIndex(id, index);

        return (T) this.objects[id];
    }

    public void remove(int id) {
        Objects.checkIndex(id, index);

        // удаляем элемент из массива
        objects[id] = null;
        // сдвигаем
        for (int i = id; i < objects.length - 1; i++) {
            var tmp = objects[i];
            objects[i] = objects[i + 1];
            objects[i + 1] = tmp;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.asList(objects).iterator();
    }
}
