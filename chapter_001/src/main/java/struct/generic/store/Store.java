package struct.generic.store;

import struct.generic.Base;

public interface Store<T extends Base> {
    void add(T model);
    boolean replace(String id, T model);
    void delete(String id);
    T findById(String id);
}
