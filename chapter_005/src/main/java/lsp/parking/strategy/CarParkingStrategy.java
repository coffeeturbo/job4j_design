package lsp.parking.strategy;

import lsp.parking.Auto;
import lsp.parking.Parking;
import lsp.parking.exception.FullParkingException;

public class CarParkingStrategy implements Strategy {

    private Parking parking;

    public CarParkingStrategy(Parking parking) {
        this.parking = parking;
    }

    @Override
    public boolean parck(Auto auto) throws Exception {
        if (auto.getSize() == 1) {

            if (parking.getMaxCarCapacity() == parking.getCurrentCarCapacity()) {
                throw new FullParkingException("Парковка для машин полна");
            }

            parking.getAutos().add(auto);
            parking.increaseCars();
            return true;
        }

        return false;
    }
}
