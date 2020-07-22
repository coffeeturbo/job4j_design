package io.console;

public class StubInput implements Input {

    private String[] input;
    private int pos = 0;

    StubInput(String[] in) {
       this.input = in;
    }

    @Override
    public String getInput() {
        return input[pos++];
    }
}
