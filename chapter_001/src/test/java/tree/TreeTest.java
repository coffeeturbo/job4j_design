package tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TreeTest {

    @Test
    public void whenAddDuplicate() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
            tree.add(1, 2),
            is(false)
        );
    }

    @Test
    public void whenAddFirstElementSuccess() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
            tree.findBy(2).isPresent(),
            is(true)
        );
    }

    @Test
    public void whenAddFirstElementFalse() {
        Tree<Integer> tree = new Tree<>(1);
        assertThat(
            tree.findBy(2).isPresent(),
            is(false)
        );
    }

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
            tree.findBy(6).isPresent(),
            is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
            tree.findBy(7).isPresent(),
            is(false)
        );
    }

}