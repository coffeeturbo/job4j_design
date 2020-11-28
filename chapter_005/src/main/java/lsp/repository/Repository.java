package lsp.repository;

import lsp.Food;

import java.util.List;

public interface Repository {
    boolean accept(Food food);
    void add(Food food);
    Food getByName(String name);
    List<Food> getAll();
    void clear();
}
