package lsp.strategy;

import lsp.ControllQuality;
import lsp.Food;
import lsp.repository.Repository;

import java.util.Date;

public class ShopStrategy extends StorageStrategy {

    public ShopStrategy(Repository repository) {
        super(repository);
    }

    @Override
    public boolean store(Food food) {
        long expired = ControllQuality.calculateProcent(food.getExpiresAt(), new Date());

        if (expired >= 25 && expired <= 75) {
            repository.add(food);
            return true;
        }

        return false;
    }
}
