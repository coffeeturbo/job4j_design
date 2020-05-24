package set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("test"));
        assertThat(it.hasNext(), is(false));
    }

}