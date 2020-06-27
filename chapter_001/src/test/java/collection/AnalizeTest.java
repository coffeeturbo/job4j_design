package collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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


}