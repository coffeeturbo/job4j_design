package io;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertThat;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSearchJsFilesSuccess() throws IOException {
        this.folder.newFile("new1.js");
        this.folder.newFile("new2.js");
        this.folder.newFile("new2.html");
        this.folder.newFile("new2.bat");
        Path tempFolderPath = Paths.get(folder.getRoot().getPath());
        List<Path> paths = Search.search(tempFolderPath, "js");
        assertThat(paths.size(), Matchers.is(2));
    }

    @Test
    public void whenPassedAllArgsSuccess()throws IOException {
        String[] args = new String[2];
        args[0] = folder.getRoot().getAbsolutePath();
        args[1] = "js";
        Search.main(args);
    }
    @Test(expected = IllegalArgumentException.class)
    public void whenSecondArgNotPassed()throws IOException {
        String[] args = new String[2];
        args[0] = folder.getRoot().getAbsolutePath();
        Search.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstArgNotPassed()throws IOException {
        String[] args = new String[2];
        Search.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAllArgNotPassed()throws IOException {
        String[] args = new String[2];
        args[1] = "js";
        Search.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPassedDirNotExists()throws IOException {
        String[] args = new String[2];
        args[0] = "not/exist/folder";
        args[1] = "js";
        Search.main(args);
    }
}