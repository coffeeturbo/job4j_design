package lsp;


import lsp.repository.Repository;
import lsp.repository.Shop;
import lsp.repository.Trash;
import lsp.repository.Warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ControllQuality {

    private final Repository shop = new Shop();
    private final Repository warehouse = new Warehouse();
    private final Repository trash = new Trash();

    public Repository getShop() {
        return shop;
    }

    public Repository getWarehouse() {
        return warehouse;
    }

    public Repository getTrash() {
        return trash;
    }

    private List<Repository> storages;

    ControllQuality() {

        this.storages = Arrays.asList(
            trash,
            shop,
            warehouse
        );
    }


    public void add(Food food) {
        for (Repository storage : this.storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> food = this.getAllFood();
        this.resetStorages();
        for (Food element : food) {
            this.add(element);
        }
    }

    private List<Food> getAllFood() {
        List<Food> foods = new ArrayList<>();

        for (Repository repository: this.storages) {
            foods.addAll(repository.getAll());
        }

        return foods;
    }

    private void resetStorages() {
        for (Repository storage: this.storages) {
            storage.clear();
        }
    }

    public static long calculateProcent(Date expires, Date now) {
        return (now.getTime() * 100L) / expires.getTime();
    }
}
