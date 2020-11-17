package lsp.parking.strategy;

import lsp.parking.Auto;
import lsp.parking.Parking;
import lsp.parking.exception.FullParkingException;

public class FullTruckParckingStrategy implements Strategy {
    private Parking parking;

    public FullTruckParckingStrategy(Parking parking) {
        this.parking = parking;
    }

    @Override
    public boolean parck(Auto auto) throws Exception {
        int totalTrucks = parking.getCurrentTruckCapacity() + auto.getSize();
        if (auto.getSize() > 1
            && totalTrucks > parking.getMaxTruckCapacity()) {

            int totalCars = parking.getCurrentCarCapacity() + auto.getSize();
            if (totalCars >= parking.getMaxCarCapacity()) {
                throw new FullParkingException("все парковки полны");
            }
            parking.getTrucks().add(auto);
            parking.increaseCars(auto.getSize());
            return true;
        }

        return false;
    }
}
