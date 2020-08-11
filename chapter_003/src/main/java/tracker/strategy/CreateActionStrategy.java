package tracker.strategy;

import sql.tracker.Store;
import tracker.Item;
import tracker.input.Input;

public class CreateActionStrategy implements UserActionStrategy {

    @Override
    public String name() {
        return "=== Create a new Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println(name());
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
