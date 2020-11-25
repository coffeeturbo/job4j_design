package lsp;

import lsp.foods.Apple;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;


public class ControllQualityTest {

    @Test
    public void whenExpired() {
        ControllQuality controll = new ControllQuality();
        Food badApple = new Apple("Тухлые", new Date(), new Date(), 100, 0);
        controll.add(badApple);
        Food actual = controll.getTrash().getByName("Тухлые");
        Assert.assertEquals("Тухлые", actual.getName());
    }

    @Test
    public void whenProductDisconted() {
        ControllQuality controll = new ControllQuality();
        Date expiresAt = addDays(new Date(), 1);
        Food badApple = new Apple("DISCONT", new Date(), expiresAt, 100, 0);
        controll.add(badApple);
        Food actual = controll.getShop().getByName("DISCONT");
        Assert.assertEquals("DISCONT", actual.getName());
        Assert.assertEquals(10, actual.getDiscount());
    }

    @Test
    public void whenProductShopWithoutDiscont() {
        ControllQuality controll = new ControllQuality();
        Date expiresAt = addDays(new Date(), 7000);
        Food badApple = new Apple("SHOP", new Date(), expiresAt, 100, 0);
        controll.add(badApple);
        Food actual = controll.getShop().getByName("SHOP");
        Assert.assertEquals("SHOP", actual.getName());
        Assert.assertEquals(0, actual.getDiscount());
    }


    @Test
    public void whenProductWarehouse() {
        ControllQuality controll = new ControllQuality();
        Date expiresAt = addDays(new Date(), 117000);
        Food badApple = new Apple("Warehouse", new Date(), expiresAt, 100, 0);
        controll.add(badApple);
        Food actual = controll.getWarehouse().getByName("Warehouse");
        Assert.assertEquals("Warehouse", actual.getName());
        Assert.assertEquals(0, actual.getDiscount());
    }

    @Test
    public void whenRsortSuccess() {

        ControllQuality controll = new ControllQuality();
        Date expiresAt = addDays(new Date(), 7000);
        Food badApple = new Apple("SHOP", new Date(), expiresAt, 100, 0);
        controll.add(badApple);
        Date expiresAt2 = addDays(new Date(), 117000);
        Food badApple2 = new Apple("Warehouse", new Date(), expiresAt2, 100, 0);
        controll.add(badApple2);

        controll.resort();
        Food actual = controll.getWarehouse().getByName("Warehouse");
        Assert.assertEquals("Warehouse", actual.getName());
        Assert.assertEquals(0, actual.getDiscount());

        Food actual2 = controll.getShop().getByName("SHOP");
        Assert.assertEquals("SHOP", actual2.getName());
        Assert.assertEquals(0, actual.getDiscount());
    }

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}