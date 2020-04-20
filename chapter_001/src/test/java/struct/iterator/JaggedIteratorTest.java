package struct.iterator;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class JaggedIteratorTest {
    @Test
    public void whenIterateTwoDimensionArray() {

        JaggedIterator iterator = new JaggedIterator(new int[][]{{1}, {4, 3, 2}, {1}});

        var hasNext = iterator.hasNext();
        var count = iterator.next();

        assertThat(hasNext, is(true));
        assertThat(count, is(1));
        count = iterator.next();
        assertThat(count, is(4));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        var it = new JaggedIterator(new int[][]{{1}, {3, 4}, {7}});
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.next(), is(4));
        MatcherAssert.assertThat(it.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        var it = new JaggedIterator(new int[][]{{1}, {3, 4}, {7}});
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.next(), is(4));
        MatcherAssert.assertThat(it.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        var it = new JaggedIterator(new int[][]{{1}, {3, 4}, {7}});
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(1));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(3));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(4));
        MatcherAssert.assertThat(it.hasNext(), is(true));
        MatcherAssert.assertThat(it.next(), is(7));
        MatcherAssert.assertThat(it.hasNext(), is(false));
    }

}