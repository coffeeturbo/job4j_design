package lsp.parking.parkings;

import lsp.parking.Auto;
import lsp.parking.Parking;
import lsp.parking.strategy.CarParkingStrategy;
import lsp.parking.strategy.FullTruckParckingStrategy;
import lsp.parking.strategy.Strategy;
import lsp.parking.strategy.TruckParkingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarTruckParking implements Parking {

    List<Auto> autos;
    List<Auto> cars;
    List<Auto> trucks;

    private final int carCapacity;
    private final int truckCapacity;
    private int currnetCarCapacity = 0;
    private int currentTruckCapacity = 0;

    private List<Strategy> parkingStrategies;

    public CarTruckParking(int carCapacity, int truckCapacity) {
        this.carCapacity = carCapacity;
        this.truckCapacity = truckCapacity;
        this.cars = new ArrayList<>(carCapacity);
        this.trucks = new ArrayList<>(truckCapacity);

        parkingStrategies = Arrays.asList(
            new CarParkingStrategy(this),
            new FullTruckParckingStrategy(this),
            new TruckParkingStrategy(this)
        );
    }

    @Override
    public boolean addAuto(Auto auto) throws Exception {
        for (Strategy strategy: this.parkingStrategies) {
            if (strategy.parck(auto)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Auto> getCars() {
        return this.cars;
    }

    public List<Auto> getTrucks() {
        return this.trucks;
    }

    @Override
    public int getMaxCarCapacity() {
        return carCapacity;
    }

    @Override
    public int getCurrentCarCapacity() {
        return currnetCarCapacity;
    }

    @Override
    public int getMaxTruckCapacity() {
        return truckCapacity;
    }

    @Override
    public int getCurrentTruckCapacity() {
        return currentTruckCapacity;
    }

    @Override
    public void increaseCars() {
        this.currnetCarCapacity++;
    }

    @Override
    public void increaseTrucks() {
        this.currentTruckCapacity++;
    }

    @Override
    public void increaseCars(int size) {
        this.currentTruckCapacity += size;
    }
}
