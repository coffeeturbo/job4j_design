package lsp.parking;

import lsp.parking.autos.Car;
import lsp.parking.autos.Truck;
import lsp.parking.parkings.CarTruckParking;

public class ParkingHandler {
    public static void main(String[] args) {
        Parking parking = new CarTruckParking(10, 10);
        Auto car = new Car(1);
        Auto truck = new Truck(2);

        try {
            parking.addAuto(car);
            parking.addAuto(truck);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(parking.getCurrentCarCapacity());
        System.out.println(parking.getCurrentTruckCapacity());
    }
}
