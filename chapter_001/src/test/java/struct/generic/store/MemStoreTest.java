package struct.generic.store;

import org.junit.Test;
import struct.generic.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MemStoreTest {
    @Test
    public void add() {
        MemStore<User> storage = new MemStore<>();
        storage.add(new User("test"));
    }

    @Test
    public void replace() {
        MemStore<User> storage = new MemStore<>();

        String input = "test";

        User oldUser = new User("oldUser");
        User expected = new User(input);

        storage.add(expected);

        storage.replace("oldUser", expected);
        User actual = storage.findById(expected.getId());

        assertNotNull(actual);
        assertThat(actual.getId(), is("test"));
    }

    @Test
    public void delete() {
        MemStore<User> storage = new MemStore<>();

        String input = "test";
        User expected = new User(input);

        storage.add(expected);

        storage.delete(input);
        User actual = storage.findById(input);

        assertNull(actual);
    }

    @Test
    public void findById() {
        MemStore<User> storage = new MemStore<>();

        String input = "test";
        User expected = new User(input);
        User badUser = new User("badUser");

        storage.add(expected);
        storage.add(badUser);

        User actual = storage.findById(input);

        assertNotNull(actual);
        assertThat(actual, is(expected));

    }
    @Test
    public void whenWronUserfindById() {
        MemStore<User> storage = new MemStore<>();

        String input = "test";
        User expected = new User(input);
        storage.add(expected);
        User actual = storage.findById("badGuyId");

        assertNull(actual);
    }
}