package lsp.parking.strategy;

import lsp.parking.Auto;
import lsp.parking.Parking;
import lsp.parking.exception.FullParkingException;

public class TruckParkingStrategy implements Strategy {

    private Parking parking;

    public TruckParkingStrategy(Parking parking) {
        this.parking = parking;
    }

    @Override
    public boolean parck(Auto auto) throws Exception {
        if (auto.getSize() > 1) {

            if (parking.getCurrentTruckCapacity() == parking.getMaxTruckCapacity()) {
                throw new FullParkingException("Парковка для грузовиков полна");
            }

            parking.getTrucks().add(auto);
            parking.increaseTrucks();
            return true;
        }

        return false;
    }
}
