package io;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenReadSuccees() {
        List<String> log = Analizy.readFile("./data/server.log");
        assertThat(log.size(), is(11));
    }

    @Test
    public void whenWriteSuccees() {
        List<String> expected = Arrays.asList("1", "2", "3");
        Analizy.write("./data/write.test", expected);
        List<String> actual = Analizy.readFile("./data/write.test");
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUnavailableSuccess() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/unavailable.csv");

        List<String> expected = Arrays.asList("10:58:01;10:59:01", "11:01:02;11:02:02");
        List<String> actual = Analizy.readFile("./data/unavailable.csv");
        assertThat(actual, is(expected));
    }



}