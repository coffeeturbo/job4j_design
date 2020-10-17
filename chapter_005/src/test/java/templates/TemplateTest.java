package templates;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertThat;

public class TemplateTest {

    @Test
    public void produce() {

        String template = "${hi}, My name is ${name}";

        Generator generator = new Template();

        Map<String, String> ags = Map.ofEntries(
            new AbstractMap.SimpleEntry<String, String>("${hi}", "Привет"),
            new AbstractMap.SimpleEntry<String, String>("${name}", "Ivan")
        );

        String actual = generator.produce(template, ags);

        assertThat(actual, Matchers.is("Привет, My name is Ivan"));

    }
}