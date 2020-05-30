package set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenHasIterator() {
        SimpleSet<String> set = new SimpleSet<>();

        assertTrue(set.iterator() instanceof Iterator);
    }

    @Test
    public void whenAdd2SameElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("test");
        set.add("test");
        set.add(null);

        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("test"));
        assertNull(it.next());
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddNullAndReturnNull() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add(null);
        Iterator<String> it = set.iterator();
        assertNull(it.next());
    }

    @Test
    public void whenAddNullAndHasTrue() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add(null);
        assertTrue(set.hasElem(null));
    }
}