package lsp.parking;

import java.util.List;

public interface Parking {
    boolean addAuto(Auto auto) throws Exception;

    List<Auto> getCars();
    List<Auto> getTrucks();
    int getMaxCarCapacity();
    int getCurrentCarCapacity();
    int getMaxTruckCapacity();
    int getCurrentTruckCapacity();

    void increaseCars();
    void increaseCars(int size);
    void increaseTrucks();
}
