package map;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void whenInsertThenGetSuccess() {
        HashMap<Integer, String> map = new HashMap<>(100);
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");

        assertThat(map.get(1), Matchers.is("first"));
        assertThat(map.get(2), Matchers.is("second"));
        assertThat(map.get(3), Matchers.is("third"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddElemThenDeleteElemThenGetElemFalse() {
        HashMap<Integer, String> map = new HashMap<>(100);
        map.insert(132, "first");
        assertTrue(map.delete(132));
        map.get(132);
    }

    @Test
    public void whenAddWithCollision() {
        HashMap<Integer, String> map = new HashMap<>(100);
        boolean rsl = map.insert(1, "first");
        assertTrue(rsl);
        rsl = map.insert(1, "first");
        assertFalse(rsl);
    }

    @Test
    public void whenFirstIterator() {
        HashMap<Integer, String> map = new HashMap<>(100);
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");

        Iterator<String> iterator = map.iterator();
        for (int i = 0; i < 3; i++) {
            assertNotNull(iterator.next());
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetElementEmpty() {
        HashMap<String, Integer> map = new HashMap<>(100);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test()
    public void whenContainerResize() {
        HashMap<Integer, String> map = new HashMap<>(12);
        int startCapacity = map.getCapacity();
        for (int i = 0; i < 20; i++) {
            map.insert(i, "index" + i);
        }
        assertNotEquals(startCapacity, map.getCapacity());
    }

}