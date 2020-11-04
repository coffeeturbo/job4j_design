package lsp.repository;

import lsp.Food;

import java.util.HashMap;
import java.util.Map;

public class Trash implements Repository {
    private final Map<String, Food> repository = new HashMap<>();

    @Override
    public void add(Food food) {
        repository.put(food.getName(), food);
    }

    @Override
    public Food getByName(String name) {
        return repository.get(name);
    }
}
