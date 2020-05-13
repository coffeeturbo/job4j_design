package struct.generic.store;


import struct.generic.Base;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int listId = indexOf(id);
        if (listId >= 0) {
            mem.set(listId, model);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        int index = indexOf(id);
        mem.remove(index);
    }

    public int indexOf(String id) {
        T elem = findById(id);
        if (elem == null) {
            return -1;
        }
        return mem.indexOf(elem);
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
