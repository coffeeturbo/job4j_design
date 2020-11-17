package lsp.parking;

import lsp.parking.autos.Car;
import lsp.parking.autos.Truck;
import lsp.parking.exception.FullParkingException;
import lsp.parking.parkings.CarTruckParking;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ParkingHandlerTest {

    @Test
    public void whenCreateParckingSuccess() {
        Parking parking = new CarTruckParking(10, 10);
        assertThat(parking.getMaxTruckCapacity(), Matchers.is(10));
        assertThat(parking.getMaxCarCapacity(), Matchers.is(10));
    }

    @Test
    public void whenAddCarSuccess() throws Exception {
        Parking parking = new CarTruckParking(10, 10);
        Auto car = new Car(1);
        parking.addAuto(car);
        int expectedSize = 1;

        assertThat(parking.getCurrentCarCapacity(), Matchers.is(expectedSize));
        assertThat(parking.getCurrentTruckCapacity(), Matchers.is(0));

    }

    @Test(expected = FullParkingException.class)
    public void whenAddCarFalse() throws Exception {
        Parking parking = new CarTruckParking(2, 10);
        Auto car = new Car(1);
        parking.addAuto(car);
        parking.addAuto(car);
        parking.addAuto(car);
    }

    @Test
    public void whenAddTruckSuccess() throws Exception {
        Parking parking = new CarTruckParking(10, 10);
        Auto car = new Truck(2);
        parking.addAuto(car);
        int expectedSize = 1;

        assertThat(parking.getCurrentTruckCapacity(), Matchers.is(expectedSize));
    }

    @Test(expected = FullParkingException.class)
    public void whenAddTruckFalse() throws Exception {
        Parking parking = new CarTruckParking(1, 1);
        Auto car = new Truck(2);
        parking.addAuto(car);
        parking.addAuto(car);
        parking.addAuto(car);
    }

    @Test
    public void whenTruckParkingFullButCarSuccess() throws Exception {
        Parking parking = new CarTruckParking(11, 1);
        Auto car = new Truck(2);
        parking.addAuto(car);

        int expectedSize = 2;
        assertThat(parking.getCurrentTruckCapacity(), Matchers.is(expectedSize));
    }

    @Test(expected = FullParkingException.class)
    public void whenWhenAllParkingFull() throws Exception {
        Parking parking = new CarTruckParking(1, 1);
        Auto car = new Truck(2);
        parking.addAuto(car);
    }
}