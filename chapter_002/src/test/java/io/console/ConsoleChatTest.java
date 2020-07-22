package io.console;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class ConsoleChatTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenInputOneWordAndFinish() throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        ConsoleChat chat = new ConsoleChat();

        Input in = new StubInput(new String[]{"Привет", "закончить"});
        File randomWords = folder.newFile("test.txt");
        populateFile(randomWords, Arrays.asList("Раз", "Два"));

        chat.init(in, randomWords.getAbsolutePath());

        List<String> expected = Arrays.asList(
            "[SYSTEM]: Введите слово-фразу",
            "[   YOU]: Привет",
            "[SYSTEM]: Введите слово-фразу",
            "[   YOU]: закончить"
        );

        assertTrue(new String(out.toByteArray()).contains(expected.get(0)));
        assertTrue(new String(out.toByteArray()).contains(expected.get(1)));
        assertTrue(new String(out.toByteArray()).contains(expected.get(2)));
        assertTrue(new String(out.toByteArray()).contains(expected.get(3)));

        System.setOut(def);

    }

    private void populateFile(File file, List<String> content) {
        try (PrintWriter out = new PrintWriter(
            new BufferedOutputStream(
                new FileOutputStream(file)
            ))) {

            content.forEach(s -> out.write(s.concat(System.lineSeparator())));

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}