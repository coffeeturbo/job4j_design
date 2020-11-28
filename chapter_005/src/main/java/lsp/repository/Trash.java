package lsp.repository;

import lsp.ControllQuality;
import lsp.Food;

import java.util.*;

public class Trash implements Repository {
    private Map<String, Food> repository = new HashMap<>();

    @Override
    public void add(Food food) {
        repository.put(food.getName(), food);
    }

    @Override
    public Food getByName(String name) {
        return repository.get(name);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public void clear() {
        repository = new HashMap<>();
    }

    @Override
    public boolean accept(Food food) {
        long expired = ControllQuality.calculateProcent(food.getExpiresAt(), new Date());

        return expired >= 100;
    }
}
