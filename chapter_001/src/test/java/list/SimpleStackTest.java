package list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenPushAndPopElem() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("value");
        assertThat(stack.pop(), is("value"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPopElemNotExist() {
        SimpleStack<String> stack = new SimpleStack<>();
        assertThat(stack.pop(), is("value"));
    }

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        assertThat(stack.pop(), is(6));
        assertThat(stack.pop(), is(5));
    }
}