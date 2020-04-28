package struct.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddGetIntegerElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        Integer expect = 12;
        array.add(expect);
        Integer actual = array.get(0);
        assertThat(expect, is(actual));
    }

    @Test
    public void whenAddGetFloatElement() {
        SimpleArray<Float> array = new SimpleArray<>(10);
        Float expect = 12.1234f;
        array.add(expect);
        Float actual = array.get(0);
        assertThat(expect, is(actual));
    }

    @Test
    public void whenSetElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.add(3);
        array.set(1, 200);
        assertThat(200, is(array.get(1)));
    }

    @Test
    public void whenRemoveElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(0);
        assertThat(2, is(array.get(0)));
        assertThat(3, is(array.get(1)));
        assertThat(null, is(array.get(2)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetUnknownElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveUnknownElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.remove(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetUnknownElement() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.set(2, 100);
    }

    @Test
    public void whenGetIterator() {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(5);
        array.add(4);
        array.add(3);
        array.add(2);
        array.add(1);
        Iterator<Integer> iterator = array.iterator();
        assertThat(5, is(iterator.next()));
        assertThat(4, is(iterator.next()));
        assertThat(3, is(iterator.next()));
        assertThat(2, is(iterator.next()));
        assertThat(1, is(iterator.next()));
    }
}