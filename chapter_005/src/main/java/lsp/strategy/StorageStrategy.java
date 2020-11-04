package lsp.strategy;

import lsp.Food;
import lsp.repository.Repository;

import java.util.Date;

public abstract class StorageStrategy {

    protected Repository repository;
    StorageStrategy(Repository repository) {
        this.repository =  repository;
    }
    abstract public boolean store(Food food);

    public static long calculateProcent(Date expires, Date now) {
        return (now.getTime() * 100L) / expires.getTime();
    }
}
