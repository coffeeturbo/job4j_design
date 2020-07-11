package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Search {

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles search =  new SearchFiles(path -> path.toString().endsWith(ext));
        Files.walkFileTree(root, search);
        return search.getPaths();
    }
}
