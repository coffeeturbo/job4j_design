package struct.generic.store;

import struct.generic.Base;

import java.util.ArrayList;
import java.util.List;

public class RoleStore<T extends Base> implements Store<T>  {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T el = findById(id);
        if (el != null) {
            int listId = mem.indexOf(el);
            mem.set(listId, model);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        T object = findById(id);
        mem.remove(object);
    }

    @Override
    public T findById(String id) {
        return mem
            .stream()
            .filter(t -> t.getId().equals(id))
            .findAny()
            .orElse(null);
    }
}
