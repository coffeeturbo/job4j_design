package lsp;


import lsp.repository.Repository;
import lsp.repository.Shop;
import lsp.repository.Trash;
import lsp.repository.Warehouse;
import lsp.strategy.*;

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

    private List<StorageStrategy> storages;

    ControllQuality() {

        this.storages = Arrays.asList(
            new TrashStrategy(trash),
            new DiscountShopStrategy(shop),
            new ShopStrategy(shop),
            new WarehouseStrategy(warehouse)
        );
    }


    public void add(Food food) {
        for (StorageStrategy storage : this.storages) {
            if (storage.store(food)) {
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

        foods.addAll(this.shop.getAll());
        foods.addAll(this.warehouse.getAll());
        foods.addAll(this.trash.getAll());

        return foods;
    }

    private void resetStorages() {
        shop.clear();
        warehouse.clear();
        trash.clear();
    }

    public static long calculateProcent(Date expires, Date now) {
        return (now.getTime() * 100L) / expires.getTime();
    }
}
