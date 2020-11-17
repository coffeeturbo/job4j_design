package lsp.parking.autos;

import lsp.parking.Auto;

public class Car implements Auto {

    private int size;

    public Car(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
