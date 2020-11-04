package lsp;


import lsp.repository.Repository;
import lsp.repository.Shop;
import lsp.repository.Trash;
import lsp.repository.Warehouse;
import lsp.strategy.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ControllQuality {

    private final Repository shop;
    private final Repository warehouse;
    private final Repository trash;

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

        this.shop = new Shop();
        this.warehouse = new Warehouse();
        this.trash = new Trash();

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

    public static long calculateProcent(Date expires, Date now) {
        return (now.getTime() * 100L) / expires.getTime();
    }
}
