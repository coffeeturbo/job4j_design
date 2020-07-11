package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("required path and ext arguments are not passed");
        }

        if (args[0] == null || args[0].isEmpty()) {
            throw new IllegalArgumentException("passed arg 0 is empty");
        }
        if (args[1] == null || args[1].isEmpty()) {
            throw new IllegalArgumentException("filter ext is not passed");
        }

        search(Paths.get(args[0]), args[1]).forEach(System.out::println);
    }
    public static List<Path> search(Path root, String ext) throws IOException {
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", root.toFile().getAbsoluteFile()));
        }

        SearchFiles search =  new SearchFiles(path -> path.toString().endsWith(ext));
        Files.walkFileTree(root, search);
        return search.getPaths();
    }
}
