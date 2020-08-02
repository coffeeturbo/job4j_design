package io.search;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchFileHandlerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenGetFilesFromDirectorySuccess() throws IOException {

        List<Path> expectedFiles = Arrays.asList(
            Paths.get(folder.newFile("test_file.txt").getPath()),
            Paths.get(folder.newFile("test_file1.txt").getPath()),
            Paths.get(folder.newFile("test_file2.txt").getPath()),
            Paths.get(folder.newFile("test_file3.txt").getPath())
        );

        List<Path> foundedFiles = SearchFileHandler.getFiles(Paths.get(folder.getRoot().getAbsolutePath()));

        assertThat(foundedFiles.size(), Matchers.is(expectedFiles.size()));
    }

    @Test
    public void whenGetFilesFromDirectoryFalse() throws IOException {

        List<Path> expectedFiles = Arrays.asList(
            Paths.get(folder.newFile("test_file.txt").getPath()),
            Paths.get(folder.newFile("test_file1.txt").getPath()),
            Paths.get(folder.newFile("test_file2.txt").getPath()),
            Paths.get(folder.newFile("test_file3.txt").getPath())
        );

        folder.newFile("test_file4.txt").getPath();

        List<Path> foundedFiles = SearchFileHandler.getFiles(Paths.get(folder.getRoot().getAbsolutePath()));
        assertNotEquals(foundedFiles.size(), expectedFiles.size());
    }


    @Test
    public void whenFilterByMatchKeySuccess() throws IOException {

        folder.newFile("test_file2.txt");
        folder.newFile("test_file.txt");
        folder.newFile("test_file1.txt");
        folder.newFile("test_file3.txt");

        SearchArgs args = new SearchArgs(new String[]{
            "-n=test_file2.txt",
            "-f"
        });

        List<Path> foundedFiles = SearchFileHandler.getFiles(
            Paths.get(folder.getRoot().getAbsolutePath()),
            args.getFilter()
        );

        assertEquals(1, foundedFiles.size());
        assertEquals("test_file2.txt", foundedFiles.get(0).getFileName().toString());
    }

    @Test
    public void whenFilterByMatchKeyNotFound() throws IOException {

        folder.newFile("test_file2.txt");
        folder.newFile("test_file.txt");
        folder.newFile("test_file1.txt");
        folder.newFile("test_file3.txt");

        SearchArgs args = new SearchArgs(new String[]{
            "-n=not_found.txt",
            "-f"
        });

        List<Path> foundedFiles = SearchFileHandler.getFiles(
            Paths.get(folder.getRoot().getAbsolutePath()),
            args.getFilter()
        );

        assertEquals(0, foundedFiles.size());
    }

    @Test
    public void whenGetFilesFromDirectoryByExtSuccess() throws IOException {

        List<Path> expectedFiles = Arrays.asList(
            Paths.get(folder.newFile("test_file.txt").getPath()),
            Paths.get(folder.newFile("test_file1.txt").getPath()),
            Paths.get(folder.newFile("test_file2.txt").getPath()),
            Paths.get(folder.newFile("test_file2.jpeg").getPath()),
            Paths.get(folder.newFile("test_file3.txt").getPath())
        );

        SearchArgs args = new SearchArgs(new String[]{
            "-n=.jpeg",
            "-m"
        });

        folder.newFile("test_file4.txt").getPath();

        List<Path> foundedFiles = SearchFileHandler.getFiles(
            Paths.get(folder.getRoot().getAbsolutePath()),
            args.getFilter()
        );

        assertEquals(1, foundedFiles.size());
        assertThat("test_file2.jpeg", is(foundedFiles.get(0).getFileName().toString()));
    }

    @Test
    public void whenGetFilesFromDirectoryByRegularExprSucces() throws IOException {

        List<Path> expectedFiles = Arrays.asList(
            Paths.get(folder.newFile("test_file.txt").getPath()),
            Paths.get(folder.newFile("test_file1.txt").getPath()),
            Paths.get(folder.newFile("test_file2.txt").getPath()),
            Paths.get(folder.newFile("test_file2.jpeg").getPath()),
            Paths.get(folder.newFile("test_file3.txt").getPath())
        );

        SearchArgs args = new SearchArgs(new String[]{
            "-n=.jpeg",
            "-m"
        });

        folder.newFile("test_file4.txt").getPath();

        List<Path> foundedFiles = SearchFileHandler.getFiles(
            Paths.get(folder.getRoot().getAbsolutePath()),
            args.getFilter()
        );

        assertEquals(1, foundedFiles.size());
        assertThat("test_file2.jpeg", is(foundedFiles.get(0).getFileName().toString()));
    }

}