package lsp;

import java.util.Date;

public class Food {
    private String name;
    private Date cratedAt;
    private Date expiresAt;
    private int price;
    private int discount;

    public Food(String name, Date cratedAt, Date expiresAt, int price, int discount) {
        this.name = name;
        this.cratedAt = cratedAt;
        this.expiresAt = expiresAt;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
            + "name='" + name + '\''
            + ", cratedAt=" + cratedAt
            + ", expiresAt=" + expiresAt
            + ", price=" + price
            + ", discount="
            + discount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;

        if (price != food.price) {
            return false;
        }
        if (discount != food.discount) {
            return false;
        }
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        result = 31 * result + discount;
        return result;
    }
}
