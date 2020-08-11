package tracker.strategy;

import sql.tracker.Store;
import tracker.Item;
import tracker.input.Input;

import java.util.List;

public class ShowAllItemsActionStrategy implements UserActionStrategy {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        int size = tracker.findAll().size();
        List<Item> items = tracker.findAll();
        for (Item item: items) {
            System.out.printf("id: %s, name: %s%n", item.getId(), item.getName());
        }
        return true;
    }
}
