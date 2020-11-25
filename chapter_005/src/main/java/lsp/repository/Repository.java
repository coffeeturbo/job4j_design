package lsp.repository;

import lsp.Food;

import java.util.List;

public interface Repository {
    void add(Food food);
    Food getByName(String name);
    List<Food> getAll();
    void clear();
}
