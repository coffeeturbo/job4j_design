package threads.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {
    @GuardedBy("this")
    private int id;
    @GuardedBy("this")
    private Integer amount;

    public User(int id, int amount) {
       this.id = id;
       this.amount = amount;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized Integer getAmount() {
        return amount;
    }

    public synchronized void setAmount(Integer amount) {
        this.amount = amount;
    }
}
