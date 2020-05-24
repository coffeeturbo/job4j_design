package collection;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T>  {
    private Node<T> head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(T value) {
        size++;
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public void revert() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
        while (current != null) {
            next = current.next;
            if (next == null) {
                head = current;
            }
            current.next = prev;
            prev = current;
            current = next;
        }
    }

    public T deleteFirst() {
        T rsl;
        if (head == null) {
            throw new NoSuchElementException();
        }

        if (head.next == null) {
            rsl = head.value;
            head = null;
        } else {
            rsl = head.value;
            head = head.next;
        }
        size--;
        return rsl;
    }

    public T deleteLast() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException();
        }

        size--;
        T rsl = head.value;
        if (head.next == null) {
            head = null;
            return rsl;
        }

        Node<T> tail = head;
        while (tail.next != null) {
            if (tail.next.next == null) {
                rsl = tail.next.value;
                tail.next = null;
                break;
            }
            tail = tail.next;
        }
        return rsl;
    }



    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
