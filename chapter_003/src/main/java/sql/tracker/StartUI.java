package sql.tracker;

import tracker.input.ConsoleInput;
import tracker.input.Input;
import tracker.input.ValidateInput;
import tracker.strategy.*;

import java.util.Arrays;
import java.util.List;

public class StartUI {

    public void init(Input input, Store tracker, List<UserActionStrategy> actions) {
        boolean run = true;

        while (run) {
            this.showMenu(actions);
            int select = Integer.valueOf(input.askInt("Select: ", actions.size()));
            UserActionStrategy action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }


    private void showMenu(List<UserActionStrategy> actions) {
        System.out.println("Menu.");
        for (UserActionStrategy action: actions) {
            System.out.println(actions.indexOf(action) + " " + action.name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input scanner = new ValidateInput(input);
        Store tracker = new SqlTracker();

        List<UserActionStrategy> actionsList =  Arrays.asList(
            new CreateActionStrategy(),
            new ShowAllItemsActionStrategy(),
            new DeleteItemActionStrategy(),
            new ReplaceItemActionStrategy(),
            new FindItemByIdActionStrategy(),
            new FindItemByNameActionStrategy()
        );

        new StartUI().init(scanner, tracker, actionsList);
    }
}
