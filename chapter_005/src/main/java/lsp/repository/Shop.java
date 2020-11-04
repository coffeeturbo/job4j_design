package lsp.repository;

import lsp.Food;

import java.util.HashMap;

public class Shop implements Repository {

    private final HashMap<String, Food> repository = new HashMap<>();

    @Override
    public void add(Food food) {
        repository.put(food.getName(), food);
    }

    @Override
    public Food getByName(String name) {
        return repository.get(name);
    }
}
