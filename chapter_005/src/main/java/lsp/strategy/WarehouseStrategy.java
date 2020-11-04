package lsp.strategy;

import lsp.ControllQuality;
import lsp.Food;
import lsp.repository.Repository;

import java.util.Date;

public class WarehouseStrategy extends StorageStrategy {

    public WarehouseStrategy(Repository repository) {
        super(repository);
    }

    @Override
    public boolean store(Food food) {
        long expired = ControllQuality.calculateProcent(food.getExpiresAt(), new Date());

        if (expired < 25) {
            repository.add(food);
            return true;
        }

        return false;
    }
}
