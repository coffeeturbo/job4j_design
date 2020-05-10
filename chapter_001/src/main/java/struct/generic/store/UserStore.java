package struct.generic.store;

import struct.generic.User;

import java.util.ArrayList;
import java.util.List;

public class UserStore implements Store<User> {

    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User model) {
        users.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        User elem = findById(id);
        if (elem != null) {
            int index = users.indexOf(elem);
            users.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        User elem = findById(id);
        if (elem != null) {
            users.remove(elem);
        }
    }

    @Override
    public User findById(String id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
        .orElse(null);
    }
}
