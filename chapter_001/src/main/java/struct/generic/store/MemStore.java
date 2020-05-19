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
        if (index != -1) {
            mem.remove(index);
        }
    }

    public int indexOf(String id) {
        for (int i = 0; i < mem.size(); i++) {
            T elem = mem.get(i);
            if (elem.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return  index != -1
            ? mem.get(index)
            : null;
    }
}
