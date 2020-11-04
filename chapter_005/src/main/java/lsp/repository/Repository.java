package lsp.repository;

import lsp.Food;

public interface Repository {
    void add(Food food);
    Food getByName(String name);
}
