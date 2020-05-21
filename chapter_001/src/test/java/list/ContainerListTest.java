package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContainerListTest {

    @Test
    public void whenAddThenGet() {
        ContainerList<String> container = new ContainerList<>();
        container.add("first");
        assertThat(container.get(0), is("first"));
    }

    @Test
    public void whenAddThenGetSecond() {
        ContainerList<String> container = new ContainerList<>();
        container.add("first");
        container.add("Second");
        assertThat(container.get(1), is("Second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddThenGetNotExistIndex() {
        ContainerList<String> container = new ContainerList<>();
        container.add("first");
        container.add("Second");
        container.get(3);
    }
    @Test
    public void whenIterator() {
        ContainerList<String> container = new ContainerList<>();
        container.add("first");
        container.add("Second");
        Iterator<String> iterator = container.iterator();

        assertThat(iterator.next(), is("first"));
        assertThat(iterator.next(), is("Second"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorElementNotExists() {
        ContainerList<String> container = new ContainerList<>();
        Iterator<String> iterator = container.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurentModifFail() {
        ContainerList<String> container = new ContainerList<>();
        container.add("first");
        Iterator<String> iterator = container.iterator();
        container.add("Second");
        iterator.next();
    }
}