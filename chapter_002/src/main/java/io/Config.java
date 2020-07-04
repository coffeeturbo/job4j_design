package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {

            read.lines().forEach(s -> {
                String[] pairs = s.split("=");
                if (!s.contains("#") && pairs.length == 2) {
                    if (!pairs[0].isEmpty() && !pairs[1].isEmpty()) {
                        values.put(pairs[0], pairs[1]);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    public String value(String key) {
        return this.values.get(key);
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_002/data/app.properties"));
    }
}
