package tracker.strategy;

import sql.tracker.Store;
import tracker.input.Input;

public class DeleteItemActionStrategy implements UserActionStrategy {
    @Override
    public String name() {
        return "=== Delete Item by id ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println(name());
        String id = input.askStr("Enter id: ");

        if (tracker.delete(id)) {
            System.out.println("positionId: " + id + " Успешно удалена!");
        }
        return true;
    }
}
