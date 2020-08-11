package tracker.strategy;

import sql.tracker.Store;
import tracker.Item;
import tracker.input.Input;

public class FindItemByIdActionStrategy implements UserActionStrategy {
    @Override
    public String name() {
        return "=== Find Item by id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");

        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("positionId: " + item.getId()
                    + " PosName: " + item.getName());
        }
        return true;
    }
}
