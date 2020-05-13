package struct.generic.store;

import struct.generic.User;

public class UserStore implements Store<User> {

    private final Store<User> users = new MemStore<>();

    @Override
    public void add(User model) {
        users.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return users.replace(id, model);
    }

    @Override
    public void delete(String id) {
        users.delete(id);
    }

    @Override
    public User findById(String id) {
        return users.findById(id);
    }
}
