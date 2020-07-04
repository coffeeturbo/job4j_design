package collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AnalizeTest {


    @Test
    public void whenDiffAddedUsers() {
        List<Analize.User> previous = Arrays.asList(
            new Analize.User(1, "Ivan"),
            new Analize.User(2, "German"),
            new Analize.User(3, "Sergey")
        );

        List<Analize.User> current = Arrays.asList(
            new Analize.User(1, "Ivan"),
            new Analize.User(2, "German"),
            new Analize.User(3, "Sergey"),
            new Analize.User(4, "John")
        );

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);

        assertEquals(1, info.added);
    }

    @Test
    public void whenDiffDeletedUsers() {
        List<Analize.User> previous = Arrays.asList(
            new Analize.User(1, "Ivan"),
            new Analize.User(2, "German"),
            new Analize.User(3, "Sergey")
        );

        List<Analize.User> current = Arrays.asList(
            new Analize.User(1, "Ivan"),
            new Analize.User(2, "German")
        );

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);

        assertEquals(1, info.deleted);
    }

    @Test
    public void whenDiffChangedUsers() {
        List<Analize.User> previous = Arrays.asList(
            new Analize.User(1, "Ivan"),
            new Analize.User(2, "German"),
            new Analize.User(3, "Sergey")
        );

        List<Analize.User> current = Arrays.asList(
            new Analize.User(1, "Ivan2"),
            new Analize.User(2, "German"),
            new Analize.User(3, "Sergey1")
        );

        Analize analize = new Analize();
        Analize.Info info = analize.diff(previous, current);

        assertEquals(2, info.changed);
    }

    @Test
    public void whenAllChanged() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(new Analize.User(1, "AA"), u2, new Analize.User(4, "D"));
        Analize.Info result = new Analize().diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.changed, is(1));
        assertThat(result.deleted, is(1));
    }

    @Test
    public void whenAddAndDelete() {
        Analize.User u1 = new Analize.User(1, "A");
        Analize.User u2 = new Analize.User(2, "B");
        Analize.User u3 = new Analize.User(3, "C");
        List<Analize.User> previous = List.of(u1, u2, u3);
        List<Analize.User> current = List.of(u1, u2, new Analize.User(33, "F"));
        Analize.Info result = new Analize().diff(previous, current);
        assertThat(result.added, is(1));
        assertThat(result.changed, is(0));
        assertThat(result.deleted, is(1));
    }


}