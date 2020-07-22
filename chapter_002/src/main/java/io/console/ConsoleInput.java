package io.console;

import java.util.Scanner;

public class ConsoleInput implements Input {

    Scanner input = new Scanner(System.in);

    @Override
    public String getInput() {
        return input.nextLine();
    }
}
