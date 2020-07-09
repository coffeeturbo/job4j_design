package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenReadSuccees() {
        List<String> log = Analizy.readFile("./data/server.log");
        assertThat(log.size(), is(6));
    }

    @Test
    public void whenUnavailableSuccess() throws IOException {
        String path = folder.newFile("unavailable.csv").getAbsolutePath();
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", path);

        List<String> expected = Arrays.asList("10:58:01;10:59:01", "11:01:02;11:02:02");
        List<String> actual = Analizy.readFile(path);
        assertThat(actual, is(expected));
    }
}