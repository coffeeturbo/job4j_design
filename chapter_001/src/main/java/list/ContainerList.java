package list;



import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ContainerList<E> implements Iterable<E> {
    private Node<E> node;
    private int size = 0;
    private int modCounter = 0;


    public E get(int index) {
        Objects.checkIndex(index, size);
        int pointer = 0;
        Node<E> elem = node;
        while (pointer < index) {
            elem = elem.next;
            pointer++;
        }

        return elem.value;
    }

    public void add(E value) {
        size++;
        modCounter++;

        Node<E> elem = new Node<>(value, null);
        if (node == null) {
            node = elem;
            return;
        }

        Node<E> tail = node;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = elem;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> tail = node;
            int expectedMod = modCounter;

            @Override
            public boolean hasNext() {
                return tail != null;
            }

            @Override
            public E next() {
                if (modCounter != expectedMod) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E value = tail.value;
                tail = tail.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
