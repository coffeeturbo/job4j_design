package tdd;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    @Test
    public void max() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7);

        MaxMin mm = new MaxMin();
        Integer actualMax = mm.max(numbers, Integer::compare);
        Integer actualMin = mm.min(numbers, Integer::compare);
        assertThat(actualMax, is(7));
        assertThat(actualMin, is(2));
    }
}