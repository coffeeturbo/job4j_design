package tracker.strategy;

import sql.tracker.Store;
import tracker.input.Input;

public interface UserActionStrategy {
    String name();

    boolean execute(Input input, Store tracker);
}
