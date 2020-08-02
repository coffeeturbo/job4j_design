package io.search;

import io.SearchFiles;

import javax.management.BadAttributeValueExpException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SearchFileHandler {
    public static void main(String[] args) {
        try {
            SearchArgs searchArgs = new SearchArgs(args);

            if (!searchArgs.validate()) {
                throw new BadAttributeValueExpException("Attributes is not valid");
            }

            List<Path> result = getFiles(
                Paths.get(searchArgs.getDirectory()), searchArgs.getFilter()
            );

            System.out.println("files founded: " + result.size());

            save(result, searchArgs.getOutputFile());
        } catch (BadAttributeValueExpException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> getFiles(Path path) {
        try {
            SearchFiles search =  new SearchFiles();
            Files.walkFileTree(path, search);
            return search.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Path> getFiles(Path path, Predicate<Path> filter) {
        try {
            SearchFiles search =  new SearchFiles(filter);
            Files.walkFileTree(path, search);
            return search.getPaths();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(List<Path> log, String fileOutput) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileOutput)))) {
            for (Path str : log) {
                out.write(str.getFileName() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
