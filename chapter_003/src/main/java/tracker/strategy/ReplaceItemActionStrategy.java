package tracker.strategy;

import sql.tracker.Store;
import tracker.Item;
import tracker.input.Input;

public class ReplaceItemActionStrategy implements UserActionStrategy {
    @Override
    public String name() {
        return "=== Edit Item by id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Введите имя item");

        Item item = new Item(name);

        if (tracker.replace(id, item)) {
            System.out.println("positionId: " + id + " Успешно отредактирована!");
        }
        return true;
    }
}
