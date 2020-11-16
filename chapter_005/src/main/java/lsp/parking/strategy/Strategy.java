package lsp.parking.strategy;

import lsp.parking.Auto;

public interface Strategy {
    boolean parck(Auto auto) throws Exception;
}
