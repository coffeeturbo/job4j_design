package lsp.parking.autos;

import lsp.parking.Auto;

public class Truck implements Auto {

    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
