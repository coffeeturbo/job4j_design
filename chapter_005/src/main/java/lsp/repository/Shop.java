package lsp.repository;

import lsp.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop implements Repository {

    private HashMap<String, Food> repository = new HashMap<>();

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
}
