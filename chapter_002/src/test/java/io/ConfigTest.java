package io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
            config.value("hibernate.connection.username"),
            is("postgres")
        );
    }
}