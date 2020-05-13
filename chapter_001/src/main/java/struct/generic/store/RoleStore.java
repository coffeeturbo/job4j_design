package struct.generic.store;

import struct.generic.Role;

public class RoleStore implements Store<Role>  {
    private final Store<Role> mem = new MemStore<>();

    @Override
    public void add(Role model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return mem.replace(id, model);
    }

    @Override
    public void delete(String id) {
        mem.delete(id);
    }

    @Override
    public Role findById(String id) {
        return mem.findById(id);
    }
}
