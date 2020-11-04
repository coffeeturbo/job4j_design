package lsp.strategy;

import lsp.ControllQuality;
import lsp.Food;
import lsp.repository.Repository;

import java.util.Date;

public class DiscountShopStrategy extends StorageStrategy {

    public DiscountShopStrategy(Repository repository) {
        super(repository);
    }

    @Override
    public boolean store(Food food) {
        long expired = ControllQuality.calculateProcent(food.getExpiresAt(), new Date());

        if (expired > 75) {
            food.setDiscount(10);
            repository.add(food);
            return true;
        }

        return false;
    }
}
