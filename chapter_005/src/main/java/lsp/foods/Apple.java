package lsp.foods;

import lsp.Food;

import java.util.Date;

public class Apple extends Food {
    public Apple(String name, Date cratedAt, Date expiresAt, int price, int discount) {
        super(name, cratedAt, expiresAt, price, discount);
    }
}
