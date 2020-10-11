package sql.tracker;

import tracker.input.ConsoleInput;
import tracker.input.Input;
import tracker.input.ValidateInput;
import tracker.strategy.*;

import java.util.Arrays;
import java.util.List;

/**
 * Используя различные утилиты для анализа памяти виртуальной машины провести анализ
 * работы программы из второго модуля. Программа заявок.
 * Данные анализа описать в текстовом файле и добавить в репозиторий.
 * В файле нужно указать, каким инструментом проведен анализ и что по нему видно.
 *
 * Анализ программы средствами jconsole
 *
 * Использовался стандартный GC G1
 * Максимальный размер кучи 4 000 МБ используется 41 Мб
 * Первый young generation запустился на 25 мб и освободид 20 мб памяти
 * Второй young generation запустился на 42 мб и освободил 35 мб
 * Количество потоков в пике 16 в постоянном 14
 * максимальная нагрузка на процессор составляла 2%
 *
 *
 *
 * Попробовать добиться состояния выхода за пределы памяти и посмотреть состояние
 * виртуальной машины.
 * Добиться OutOfMemoryError при выводе 9000 обьектов из бд
 * -Xms3m -Xmx5m
 * При отладке jconsole теряет connection и выполнение программы завершается с исключением
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
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
