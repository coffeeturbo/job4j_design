package threads.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {

        if (users.get(user.getId()) != null) {
            return false;
        }

        users.put(user.getId(), user);
        return true;
    }

    public synchronized boolean update(User user) {
        User exist = users.get(user.getId());

        if (exist != null) {
            users.put(user.getId(), user);
            return true;
        }

        return false;
    }

    public synchronized boolean delete(User user) {
        User exist = users.get(user.getId());

        if (exist != null) {
            users.remove(user.getId());
            return true;
        }

        return false;
    }

    public synchronized void transfer(int fromId, int toId, int amount) throws Exception {
        User from = users.get(fromId);

        if (from == null) {
            throw new Exception("Source user not exists");
        }

        if (from.getAmount() < amount) {
            throw new Exception("source user has not enought amount");
        }

        User target = users.get(toId);
        if (target == null) {
            throw new Exception("target user not exists");
        }

        from.setAmount(from.getAmount() - amount);
        target.setAmount(target.getAmount() + amount);
    }
}
