package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JaggedIteratorTest {

    @Test
    public void whenIterateTwoDimensionArray() {
        int[][] input = {
            {3}, {2}, {1}
        };

        JaggedIterator jagger = new JaggedIterator(input);
        assertThat(jagger.next(), is(3));
        assertThat(jagger.next(), is(2));
        assertThat(jagger.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmptyArray() {

        int[][] input = {};
        JaggedIterator jagger = new JaggedIterator(input);
        jagger.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmptyMultyArray() {
        int[][] input = {{}};
        JaggedIterator jagger = new JaggedIterator(input);
        jagger.next();
    }

    @Test
    public void whenIterateTwoDimensionArrayFirstEmpty() {
        int[][] input = {
            {}, {2}, {1}
        };

        JaggedIterator jagger = new JaggedIterator(input);
        assertThat(jagger.next(), is(2));
        assertThat(jagger.next(), is(1));
    }

    @Test
    public void whenIterateTwoDimensionArraySecondEmpty() {
        int[][] input = {
            {3}, {}, {1}
        };

        JaggedIterator jagger = new JaggedIterator(input);
        assertThat(jagger.next(), is(3));
        assertThat(jagger.next(), is(1));
    }

    @Test
    public void whenIterateTwoDimensionArrayLastEmpty() {
        int[][] input = {
            {3}, {2}, {}
        };

        JaggedIterator jagger = new JaggedIterator(input);
        assertThat(jagger.next(), is(3));
        assertThat(jagger.next(), is(2));
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
            {1}, {}, {}, {}, {2}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
            {1}, {2, 3}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test
    public void when4El() {
        int[][] in = {
            {1}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
            {}, {1}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
            {}, {1}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
            {}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
            {}
        };
        JaggedIterator it = new JaggedIterator(in);
        it.next();
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
            {}, {1}
        };
        JaggedIterator it = new JaggedIterator(in);
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }
}