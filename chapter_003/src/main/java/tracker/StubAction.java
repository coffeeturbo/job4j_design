package tracker;

import sql.tracker.Store;
import tracker.input.Input;
import tracker.strategy.UserActionStrategy;

public class StubAction implements UserActionStrategy {
    private boolean call = false;

    @Override
    public String name() {
        return "Stub name";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        call = true;
        return false;
    }

    public boolean isCall() {
        return call;
    }
}
